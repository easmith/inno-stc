package ru.easmith;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.easmith.jaxbmodels.*;

import java.util.Collections;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    static {
//        DOMConfigurator.configure("log4j.xml");
        PropertyConfigurator.configure("log4j.properties");
    }

    public static void main(String args[]) {
        LOGGER.trace("Работу начал");
//        DatabaseManager.getInstance().clearDB();
//        createXml();
        xmlToDB();
//        dbToXml();
        LOGGER.trace("Работу завершил");
    }

    /**
     * Создает XML файлы из Java обектов
     */
    public static void createXml() {
        LOGGER.info("Создаем XML файлы:");

        MyObjectFactory factory = new MyObjectFactory();
        CategoryList categoryList = factory.createCategoryList();

        Category category = factory.createCategory("Junior");
        for (int i = 0; i < 20; i++) {
            Task task = factory.createTask(category.getId(), "");
            task.setText(category.getName() + " task #" + task.getId());
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

        for (int i = 0; i < 5; i++) {
            User user = factory.createUser("user_" + i, "UserName" + i, "pass", false);
            userList.getUser().add(user);
            Result result = factory.createResult(category.getId(), user.getId());
            resultList.getResult().add(result);

            // перемешиваем вопросы
            Collections.shuffle(category.getTasks());

            for (int j = 0; j < 5; j++) {
                Task task = category.getTasks().get(j);

                // перемешиваем ответы
                Collections.shuffle(task.getAnswers());
                // выбираем один ответ
                String answers = task.getAnswers().get(0).getId() + "";
                ResultTask resultTask = factory.createResultTask(result.getId(), task.getId(), answers);

                resultTaskList.getResultTask().add(resultTask);
            }
        }

        XmlManager.exportObject(categoryList, "categoryList.xml");
        XmlManager.exportObject(userList, "userList.xml");
        XmlManager.exportObject(resultList, "resultList.xml");
        XmlManager.exportObject(resultTaskList, "resultTaskList.xml");
    }

    /**
     * Загружает XML в базу данных
     */
    public static void xmlToDB() {
        CategoryList categoryList = (CategoryList) XmlManager.importObject("categoryList.xml", CategoryList.class);
        UserList userList = (UserList) XmlManager.importObject("userList.xml", UserList.class);
        ResultList resultList = (ResultList) XmlManager.importObject("resultList.xml", ResultList.class);
        ResultTaskList resultTaskList = (ResultTaskList) XmlManager.importObject("resultTaskList.xml", ResultTaskList.class);

        DatabaseManager.getInstance().clearDB();
        DatabaseManager.getInstance().storeObject(categoryList);
        DatabaseManager.getInstance().storeObject(userList);
        DatabaseManager.getInstance().storeObject(resultList);
        DatabaseManager.getInstance().storeObject(resultTaskList);
    }

    /**
     * Выгружает базу данных в XML
     */
    public static void dbToXml() {
        CategoryList categoryList = (CategoryList)DatabaseManager.getInstance().restoreObject(new CategoryList());
        UserList userList = (UserList)DatabaseManager.getInstance().restoreObject(new UserList());
        ResultList resultList = (ResultList)DatabaseManager.getInstance().restoreObject(new ResultList());
        ResultTaskList resultTaskList = (ResultTaskList)DatabaseManager.getInstance().restoreObject(new ResultTaskList());

        XmlManager.exportObject(categoryList, "fromDB_categoryList.xml");
        XmlManager.exportObject(userList, "fromDB_userList.xml");
        XmlManager.exportObject(resultList, "fromDB_resultList.xml");
        XmlManager.exportObject(resultTaskList, "fromDB_resultTaskList.xml");
    }
}
