package ru.easmith;

import ru.easmith.jaxbmodels.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    private final String asd = "ASD";

    public static void main(String args[]) {

        ObjectFactory objectFactory = new ObjectFactory();
        Categories categories = objectFactory.createCategories();

        Category category = objectFactory.createCategory("Junior");
        for (int i = 0; i < 20; i++) {
            Task task = objectFactory.createTask(category.getId(), category.getName() + " task #" + i);
            for (int j = 0; j < 4; j++) {
                Answer answer = objectFactory.createAnswer(task.getId(), "Answer for task " + task.getId(), (i + j) % 4 == 0);
                task.getAnswers().add(answer);
            }
            category.getTasks().add(task);
        }
        categories.getCategory().add(category);

        XmlManager.exportObject(categories.getClass(), categories, "categories.xml");
        Categories categories1 = (Categories) XmlManager.importObject("categories.xml");
        System.out.println(categories1.getCategory().get(0).getName());

    }
}
