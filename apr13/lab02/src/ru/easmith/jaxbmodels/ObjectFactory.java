
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
public class ObjectFactory {

    protected static int categoryCounter = 0;
    protected static int taskCounter = 0;
    protected static int answerCounter = 0;


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.easmith.jaxbmodels
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer(int taskId, String text, Boolean isCorrect) {
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
     */
    public Task createTask(int categoryId, String text) {
        Task task = new Task();
        task.setId(++taskCounter);
        task.setCategoryId(categoryId);
        task.setText(text);
        return task;
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory(String name) {
        Category category = new Category();
        category.setId(++categoryCounter);
        category.setName(name);

        return category;
    }

    /**
     * Create an instance of {@link Categories }
     * 
     */
    public Categories createCategories() {
        return new Categories();
    }

}
