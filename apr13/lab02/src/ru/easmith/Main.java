package ru.easmith;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.easmith.jaxbmodels.*;
import ru.easmith.threads.DbToXmlThread;
import ru.easmith.threads.XmlToDbCareThread;
import ru.easmith.threads.XmlToDbThread;
import ru.easmith.utils.DatabaseManager;
import ru.easmith.utils.XmlManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    static {
//        DOMConfigurator.configure("log4j.xml");
        PropertyConfigurator.configure("log4j.properties");
    }

    public static void main(String args[]) {
        LOGGER.trace("Работу начал");
        DatabaseManager.getInstance().clearDB();
        createXml();
        xmlToDB();
//        dbToXml();
//        dbToXmlMultiThread();
//        xmlToDbMultiThread();
//        xmlToDbMultiThreadCare();
//        xmlToDbMultiThreadCare2();
        LOGGER.trace("Работу завершил");
    }

    /**
     * Создает XML файлы из Java обектов
     */
    public static void createXml() {
        LOGGER.info("Создаем XML файлы");
        long startTime = System.currentTimeMillis();

        MyObjectFactory factory = new MyObjectFactory();
        CategoryList categoryList = factory.createCategoryList();
        Category category = factory.createCategory("Junior");

        // всего в базе 500 вопросов
        for (int i = 0; i < 500; i++) {
            Task task = factory.createTask(category.getId(), "");
            task.setText(category.getName() + " task #" + task.getId());
            // по 4 ответа на вопрос
            for (int j = 0; j < 4; j++) {
                Answer answer = factory.createAnswer(task.getId(), "", (i + j) % 4 == 0);
                answer.setText("Answer #" + answer.getId() + " for task #" + task.getId());
                task.getAnswers().add(answer);
            }
            category.getTasks().add(task);
        }
        categoryList.getCategory().add(category);

        UserList userList = factory.createUserList();
        ResultList resultList = factory.createResultList();
        ResultTaskList resultTaskList = factory.createResultTaskList();

        User admin = factory.createUser("admin", "Eugene", "pass", true);
        userList.getUser().add(admin);

        // + 100 пользователей
        for (int i = 0; i < 100; i++) {
            User user = factory.createUser("user_" + i, "UserName" + i, "pass", false);
            userList.getUser().add(user);

            // Каждый пользователь прошел тест по 5 раз
            for (int j = 0; j < 5; j++) {
                Result result = factory.createResult(category.getId(), user.getId());
                resultList.getResult().add(result);

                // перемешиваем вопросы
                Collections.shuffle(category.getTasks());

                // в каждом тесте по 5 вопросов
                for (int k = 0; k < 10; k++) {
                    Task task = category.getTasks().get(k);

                    // перемешиваем ответы
                    Collections.shuffle(task.getAnswers());
                    // выбираем один ответ
                    String answers = task.getAnswers().get(0).getId() + "";
                    ResultTask resultTask = factory.createResultTask(result.getId(), task.getId(), answers);

                    resultTaskList.getResultTask().add(resultTask);
                }
            }
        }

        XmlManager.exportObject(categoryList, "categoryList.xml");
        XmlManager.exportObject(userList, "userList.xml");
        XmlManager.exportObject(resultList, "resultList.xml");
        XmlManager.exportObject(resultTaskList, "resultTaskList.xml");

        LOGGER.info("Файлы созданы за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Загружает XML в базу данных
     */
    public static void xmlToDB() {
        LOGGER.info("Загружаем XML в базу данных");

        CategoryList categoryList = (CategoryList) XmlManager.importObject("categoryList.xml", CategoryList.class);
        UserList userList = (UserList) XmlManager.importObject("userList.xml", UserList.class);
        ResultList resultList = (ResultList) XmlManager.importObject("resultList.xml", ResultList.class);
        ResultTaskList resultTaskList = (ResultTaskList) XmlManager.importObject("resultTaskList.xml", ResultTaskList.class);
        LOGGER.info("XML файлы прочитаны");
        long startTime = System.currentTimeMillis();

//        DatabaseManager.getInstance().clearDB();
        DatabaseManager.getInstance().storeObject(categoryList);
//        DatabaseManager.getInstance().storeObject(userList);
//        DatabaseManager.getInstance().storeObject(resultList);
//        DatabaseManager.getInstance().storeObject(resultTaskList);

        LOGGER.info("Файлы загружены за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Выгружает базу данных в XML последовательно
     */
    public static void dbToXml() {
        LOGGER.info("Выгружаем из базы в XML");
        long startTime = System.currentTimeMillis();

        CategoryList categoryList = (CategoryList) DatabaseManager.getInstance().restoreObject(new CategoryList());
        UserList userList = (UserList) DatabaseManager.getInstance().restoreObject(new UserList());
        ResultList resultList = (ResultList) DatabaseManager.getInstance().restoreObject(new ResultList());
        ResultTaskList resultTaskList = (ResultTaskList) DatabaseManager.getInstance().restoreObject(new ResultTaskList());

        XmlManager.exportObject(categoryList, "fromDB_categoryList.xml");
        XmlManager.exportObject(userList, "fromDB_userList.xml");
        XmlManager.exportObject(resultList, "fromDB_resultList.xml");
        XmlManager.exportObject(resultTaskList, "fromDB_resultTaskList.xml");

        LOGGER.info("Файлы выгружены за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Выгружает базу данных в XML в несколько потоков
     */
    public static void dbToXmlMultiThread() {
        LOGGER.info("Многопоточно выгружаем из базы в XML");
        long startTime = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        ArrayList<Future> futures = new ArrayList<>();

        futures.add(threadPool.submit(new DbToXmlThread(new CategoryList(), "thread_categoryList.xml")));
        futures.add(threadPool.submit(new DbToXmlThread(new UserList(), "thread_userList.xml")));
        futures.add(threadPool.submit(new DbToXmlThread(new ResultList(), "thread_resultList.xml")));
        futures.add(threadPool.submit(new DbToXmlThread(new ResultTaskList(), "thread_resultTaskList.xml")));

        waitFuture(futures);

        threadPool.shutdown();

        LOGGER.info("Многопоточно файлы выгружены за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Выгружает базу данных в XML в несколько потоков, отключая проверку внешних ключей
     */
    public static void xmlToDbMultiThread() {
        LOGGER.info("Многопоточно загружаем в базу из XML");
        long startTime = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        DatabaseManager.getInstance().clearDB();
        DatabaseManager.getInstance().disableForeignKey();

        ArrayList<Future> futures = new ArrayList<>();

        futures.add(threadPool.submit(new XmlToDbThread(CategoryList.class, "thread_categoryList.xml")));
        futures.add(threadPool.submit(new XmlToDbThread(UserList.class, "thread_userList.xml")));
        futures.add(threadPool.submit(new XmlToDbThread(ResultList.class, "thread_resultList.xml")));
        futures.add(threadPool.submit(new XmlToDbThread(ResultTaskList.class, "thread_resultTaskList.xml")));

        waitFuture(futures);

        DatabaseManager.getInstance().enableForeignKey();

        threadPool.shutdown();
        LOGGER.info("Многопоточно файлы загружены за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Выгружает базу данных в XML в несколько потоков поочередно
     */
    public static void xmlToDbMultiThreadCare() {
        LOGGER.info("Многопоточно загружаем в базу из XML");
        long startTime = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        DatabaseManager.getInstance().clearDB();
        ArrayList<Future> futures = new ArrayList<>();

        futures.add(threadPool.submit(new XmlToDbThread(CategoryList.class, "thread_categoryList.xml")));
        futures.add(threadPool.submit(new XmlToDbThread(UserList.class, "thread_userList.xml")));

        waitFuture(futures);
        futures.add(threadPool.submit(new XmlToDbThread(ResultList.class, "thread_resultList.xml")));
        waitFuture(futures);
        futures.add(threadPool.submit(new XmlToDbThread(ResultTaskList.class, "thread_resultTaskList.xml")));
        waitFuture(futures);

        threadPool.shutdown();
        LOGGER.info("Многопточно файлы загружены за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Выгружает базу данных в XML в несколько потоков с учетом зависимостей
     */
    public static void xmlToDbMultiThreadCare2() {
        LOGGER.info("Многопоточно загружаем в базу из XML");
        long startTime = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        DatabaseManager.getInstance().clearDB();
        ArrayList<Future> futures = new ArrayList<>();

        Set<String> monitor = new HashSet<>();

        futures.add(threadPool.submit(new XmlToDbCareThread(CategoryList.class, "thread_categoryList.xml", monitor)));
        futures.add(threadPool.submit(new XmlToDbCareThread(UserList.class, "thread_userList.xml", monitor)));
        futures.add(threadPool.submit(new XmlToDbCareThread(ResultList.class, "thread_resultList.xml", monitor)));
        futures.add(threadPool.submit(new XmlToDbCareThread(ResultTaskList.class, "thread_resultTaskList.xml", monitor)));

        waitFuture(futures);

        threadPool.shutdown();
        LOGGER.info("Многопоточно файлы загружены за " + (System.currentTimeMillis() - startTime) + " мс");
    }

    /**
     * Служит для ожидания результатов работы потоков
     * @param futures
     */
    public static void waitFuture(ArrayList<Future> futures) {
        for (Future future :
                futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                LOGGER.error("Wait feature InterruptedException: " + e.getMessage());
            } catch (ExecutionException e) {
                LOGGER.error("Wait feature ExecutionException: " + e.getMessage());
            }
        }
    }
}
