
package ru.easmith.jaxbmodels;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ru.easmith.jaxbmodels package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class MyObjectFactory {

    protected static int categoryCounter = 0;
    protected static int taskCounter = 0;
    protected static int answerCounter = 0;
    protected static int userCounter = 0;
    protected static int resultCounter = 0;
    protected static int resultTaskCounter = 0;


    /**
     * Create a new MyObjectFactory that can be used to create new instances of schema derived classes for package: ru.easmith.jaxbmodels
     *
     */
    public MyObjectFactory() {
    }

    /**
     * Create an instance of {@link Answer }
     *
     * @param taskId Id заданния
     * @param text Текст ответа
     * @param isCorrect Это правильный ответ?
     * @return {@link Answer }
     */
    public static Answer createAnswer(int taskId, String text, Boolean isCorrect) {
        Answer answer = new Answer();
        answer.setId(++answerCounter);
        answer.setTaskId(taskId);
        answer.setText(text);
        answer.setIsCorrect(isCorrect);

        return answer;
    }

    /**
     * Create an instance of {@link Task }
     *
     * @param categoryId Id категории
     * @param text Текст задания
     * @return {@link Task }
     */
    public static Task createTask(int categoryId, String text) {
        Task task = new Task();
        task.setId(++taskCounter);
        task.setCategoryId(categoryId);
        task.setText(text);
        return task;
    }

    /**
     * Create an instance of {@link Category }
     *
     * @param name Название категории
     * @return {@link Category }
     */
    public static Category createCategory(String name) {
        Category category = new Category();
        category.setId(++categoryCounter);
        category.setName(name);

        return category;
    }

    /**
     * Создает пользователя
     *
     * @param login Логин
     * @param name Имя
     * @param password Пароль
     * @param isAdmin Это администратор?
     * @return {@link User }
     */
    public static User createUser(String login, String name, String password, boolean isAdmin) {
        User user = new User();
        user.setId(++userCounter);
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        user.setIsAdmin(isAdmin);

        return user;
    }

    /**
     * Создает результат
     *
     * @param categoryId Id кактегории заданий
     * @param userId Id пользователя
     * @return {@link Result }
     */
    public static Result createResult(int categoryId, int userId) {
        Result result = new Result();
        result.setId(++resultCounter);
        result.setCategoryId(categoryId);
        result.setUserId(userId);

        return result;
    }

    /**
     * Создает выбранное решение для результата и соответствующего названия
     *
     * @param resultId Id результата
     * @param taskId Id задания
     * @param answers Пересичленные через запятую выбранные ответы
     * @return {@link ResultTask }
     */
    public static ResultTask createResultTask(int resultId, int taskId, String answers) {
        ResultTask resultTask = new ResultTask();

        resultTask.setId(++resultTaskCounter);
        resultTask.setResultId(resultId);
        resultTask.setTaskId(taskId);
        resultTask.setAnswers(answers);

        return resultTask;
    }

    /**
     * Создает список для категорий
     *
     * @return {@link CategoryList }
     */
    public static CategoryList createCategoryList() {
        return new CategoryList();
    }

    /**
     * Создает список для пользователей
     *
     * @return {@link UserList }
     */
    public static UserList createUserList() {
        return new UserList();
    }

    /**
     * Создает список для результатов
     *
     * @return {@link ResultList }
     */
    public static ResultList createResultList() {
        return new ResultList();
    }

    /**
     * Создает список для заданий в результатах
     *
     * @return {@link ResultTaskList }
     */
    public static ResultTaskList createResultTaskList() {
        return new ResultTaskList();
    }
}
