package ru.easmith;

import ru.easmith.jaxbmodels.Categories;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by eku on 15.04.17.
 */
public class XmlManager {


    public static void exportObject(Class clazz, Object object, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File(fileName);
            marshaller.marshal(object, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Object importObject(String fileName) {
        Object object = new Object();
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            return object;
        }

    }
}
