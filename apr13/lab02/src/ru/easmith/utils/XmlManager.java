package ru.easmith.utils;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by eku on 15.04.17.
 */
public class XmlManager {

    private static final Logger LOGGER = Logger.getLogger(XmlManager.class);


    /**
     * Маршаллинг объекта в XML файл
     *
     * @param object экспортируемый объект
     * @param fileName имя файла в директори xml/
     */
    public static void exportObject(Object object, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File("xml/" + fileName);
            marshaller.marshal(object, file);
            LOGGER.trace("Объект сохранен в файл: " + fileName);
        } catch (JAXBException e) {
            LOGGER.fatal("Не удалось экспортировать файл '" + fileName + "':" + e.getMessage());
        }
    }

    /**
     * Импорт XML в экземпляр класса clazz
     *
     * @param fileName Имя файла
     * @param clazz Класс
     * @return Object
     */
    public static Object importObject(String fileName, Class clazz) {
        Object object = new Object();
        try {
            File file = new File("xml/" + fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(file);
            LOGGER.trace("Объект загружен из файла: " + fileName);
        } catch (JAXBException e) {
            LOGGER.fatal("Не удалось импортировать файл '" + fileName + "':" + e.getMessage());
        } finally {
            return object;
        }
    }
}
