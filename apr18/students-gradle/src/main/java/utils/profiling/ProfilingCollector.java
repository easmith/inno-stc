package utils.profiling;

import org.apache.log4j.Logger;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by eku on 26.04.17.
 */
public class ProfilingCollector {
    CopyOnWriteArrayList collection = new CopyOnWriteArrayList();

    private static final Logger LOGGER = Logger.getLogger(ProfilingCollector.class);

    public void saveLog(String string) {
        LOGGER.info("Collect " + string);
        collection.add(string);
    }
}
