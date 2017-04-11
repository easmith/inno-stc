import org.junit.jupiter.api.Test;
import ru.easmith.FileChecker.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eku-win on 10.04.2017.
 */
public class MainTest {

    @Test
    public void resourceDetectorTest() {
        assertEquals("file", ResourceDetector.detect("thisIsFile.txt"));
        assertEquals("file", ResourceDetector.detect("file:///home/user/file.txt"));
        assertEquals("url", ResourceDetector.detect("http://site.com/file.txt"));
        assertEquals("url", ResourceDetector.detect("https://site.com/file.txt"));
        assertEquals(null, ResourceDetector.detect("ftp://site.com:21/file.txt"));
    }

    @Test
    public void wordCheckerTest() {
        assertTrue(WordChecker.isValid("Тест"));
        assertTrue(WordChecker.isValid("тёст"));
        assertTrue(WordChecker.isValid("тЁст"));
        assertFalse(WordChecker.isValid("тесt"));
        assertFalse(WordChecker.isValid("123"));
    }

    @Test
    public void resourceStreamTest() {
        Throwable exception1 = assertThrows(IOException.class, () -> {
            ResourceStream resourceStream = new ResourceStream("fileDoesNotExist.txt");
        });

        try {
            ResourceStream resourceStream = new ResourceStream("texts/file0.txt");
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void resourceParserTest() {
        WordSet wordSet = new WordSet();
        ResourceParser.parse("texts/file0.txt", wordSet, new ReentrantLock());

        assertEquals(10000, wordSet.size());
    }

    @Test
    public void founDuplicate() {
        ReentrantLock locker = new ReentrantLock();
        String[] resources = {"texts/file0.txt", "texts/file99.txt"};

        ExecutorService threadPool = Executors.newCachedThreadPool();
        ArrayList<Future> futures = new ArrayList<>();
        for (String resource :
                resources) {
            futures.add(threadPool.submit(new FileChecker(resource, locker)));
        }

        for (Future future :
                futures) {
            try {
                assertEquals("texts/file99.txt: дубликат 'ГНюТЦФиц'", futures.get(1).get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
