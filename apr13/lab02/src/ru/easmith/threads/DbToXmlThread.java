package ru.easmith.threads;

import org.apache.log4j.Logger;
import ru.easmith.DBInterface;
import ru.easmith.DatabaseManager;
import ru.easmith.Main;
import ru.easmith.XmlManager;

/**
 * Created by eku on 16.04.17.
 */
public class DbToXmlThread implements Runnable {
    protected static final Logger LOGGER = Logger.getLogger(DbToXmlThread.class);

    private DBInterface object;
    private String fileName;

    public DbToXmlThread(DBInterface object, String fileName) {
        this.object = object;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        LOGGER.trace("Старт потока для " + this.fileName);
        DatabaseManager.getInstance().restoreObject(this.object);
        XmlManager.exportObject(this.object, this.fileName);
        LOGGER.trace("Завершение потока для " + this.fileName);
    }
}
