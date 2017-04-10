package ru.easmith.FileChecker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by eku-win on 10.04.2017.
 */
public class ResourceStream extends InputStream {
    private InputStream resourceStream;

    public ResourceStream(String resourceName) throws IOException {
        String resourceType = ResourceDetector.detect(resourceName);

        if (resourceType == "file") {
            this.resourceStream = new FileInputStream(resourceName);
        } else if (resourceType == "url") {
            this.resourceStream = new URL(resourceName).openStream();
        } else {
            new IOException("Unknown resource '" + resourceName + "'");
        }
    }

    @Override
    public int read() throws IOException {
        return resourceStream.read();
    }

    @Override
    public int read(byte b[]) throws IOException {
        return resourceStream.read(b);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        return resourceStream.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        resourceStream.close();
    }
}
