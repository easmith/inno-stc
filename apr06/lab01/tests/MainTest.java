import org.junit.jupiter.api.Test;
import ru.easmith.FileChecker.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eku-win on 10.04.2017.
 */
public class MainTest {

    @Test
    public void resourceDetectorTest() {
        assertEquals(ResourceDetector.detect("thisIsFile.txt"), "file");
        assertEquals(ResourceDetector.detect("file:///home/user/file.txt"), "file");
        assertEquals(ResourceDetector.detect("http://site.com/file"), "url");
        assertEquals(ResourceDetector.detect("https://site.com/file"), "url");
        assertEquals(ResourceDetector.detect("ftp://site.com:21/file"), null);
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
        ResourceParser.parse("texts/file0.txt", wordSet);

        assertEquals(10000, wordSet.size());
    }
}
