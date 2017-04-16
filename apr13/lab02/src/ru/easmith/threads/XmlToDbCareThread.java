package ru.easmith.threads;

import com.sun.deploy.util.ArrayUtil;
import org.apache.log4j.Logger;
import ru.easmith.jaxbmodels.DBInterface;
import ru.easmith.utils.DatabaseManager;
import ru.easmith.utils.XmlManager;

import java.util.Set;

/**
 * Created by eku on 16.04.17.
 */
public class XmlToDbCareThread implements Runnable {
    protected static final Logger LOGGER = Logger.getLogger(XmlToDbCareThread.class);

    private Class clazz;
    private String fileName;
    private Set monitor;

    public XmlToDbCareThread(Class clazz, String fileName, Set monitor) {
        this.clazz = clazz;
        this.fileName = fileName;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        LOGGER.trace("Старт потока для " + this.fileName);
        DBInterface object = (DBInterface) XmlManager.importObject(this.fileName, this.clazz);
        if (object.getDepends() != null) {
            LOGGER.debug(fileName + " зависит от " + ArrayUtil.arrayToString(object.getDepends()));
            while (true) {
                synchronized (monitor) {
                    boolean canContinue = true;
                    for (String depend :
                            object.getDepends()) {
                        canContinue = canContinue && monitor.contains(depend);
                    }
                    if (canContinue) {
                        LOGGER.debug(fileName + " зависити удовлетворены, загружаем в базу...");
                        break;
                    }
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DatabaseManager.getInstance().storeObject(object);
        synchronized (monitor) {
            monitor.add(object.getClass().getName());
            monitor.notifyAll();
        }
        LOGGER.trace("Завершение потока для " + this.fileName);
    }
}
