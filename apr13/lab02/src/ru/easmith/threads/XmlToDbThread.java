package ru.easmith.threads;

import org.apache.log4j.Logger;
import ru.easmith.jaxbmodels.DBInterface;
import ru.easmith.utils.DatabaseManager;
import ru.easmith.utils.XmlManager;

/**
 * Created by eku on 16.04.17.
 */
public class XmlToDbThread implements Runnable {
    protected static final Logger LOGGER = Logger.getLogger(XmlToDbThread.class);

    private Class clazz;
    private String fileName;

    public XmlToDbThread(Class clazz, String fileName) {
        this.clazz = clazz;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        LOGGER.trace("Старт потока для " + this.fileName);
        DBInterface object = (DBInterface)XmlManager.importObject(this.fileName, this.clazz);
        DatabaseManager.getInstance().storeObject(object);
        LOGGER.trace("Завершение потока для " + this.fileName);
    }
}
