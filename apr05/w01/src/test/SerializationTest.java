package test;

import com.company.library.Library;
import com.company.library.Utils.DataManager;
import com.company.library.models.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by Arch on 10.04.2017.
 */
public class SerializationTest {

    private static DataManager dataManager;

    @BeforeAll
    public static void init() {
        dataManager = new DataManager();
    }

    @Test
    public void testDeserializationBook() {
        File file = mock(File.class);
        FileReader fileReader =  mock(FileReader.class);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("com_company_library_models_Book.txt"))) {
            stringBuilder.append(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            when(bufferedReader.readLine()).thenReturn(stringBuilder.toString());
//            Collection<Book> collection = dataManager.uDeserialize(bufferedReader.readLine());
//            Book book = collection.iterator().next();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
