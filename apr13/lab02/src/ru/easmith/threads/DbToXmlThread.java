package ru.easmith.threads;

import org.apache.log4j.Logger;
import ru.easmith.jaxbmodels.DBInterface;
import ru.easmith.utils.DatabaseManager;
import ru.easmith.utils.XmlManager;

import java.util.concurrent.Callable;

/**
 * Created by eku on 16.04.17.
 */
public class DbToXmlThread implements Callable {
    protected static final Logger LOGGER = Logger.getLogger(DbToXmlThread.class);

    private DBInterface object;
    private String fileName;

    public DbToXmlThread(DBInterface object, String fileName) {
        this.object = object;
        this.fileName = fileName;
    }

    @Override
    public Boolean call() {
        LOGGER.trace("Старт потока для " + this.fileName);
        DatabaseManager.getInstance().restoreObject(this.object);
        XmlManager.exportObject(this.object, this.fileName);
        LOGGER.trace("Завершение потока для " + this.fileName);
        return true;
    }
}
