package ru.easmith.FileChecker;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by eku-win on 10.04.2017.
 */
public class ResourceDetector {

    /**
     * Определяет тип ресура по имени
     * @param resourceName
     * @return
     */
    public static String detect(String resourceName) {
        URI uri = null;
        try {
            uri = new URI(resourceName);
        } catch (URISyntaxException e) {
            System.err.println("Ошибка в названии: " + resourceName);
            return null;
        }

        if (uri == null) {
            System.err.println("Не удалось определить: " + resourceName);
            return null;
        }

        if (uri.getScheme() == null || uri.getScheme().equals("file")) {
            return "file";
        } else if (uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
            return "url";
        }

        return null;
    }
}
