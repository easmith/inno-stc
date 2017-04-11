package test;

import com.company.library.Utils.DataManager;
import com.company.library.Utils.DataManagerInterface;
import com.company.library.models.Book;
import com.company.library.models.Model;
import com.company.library.models.ModelInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
    public void bookToXmlTest() {
        ModelInterface book = (ModelInterface) Proxy.newProxyInstance(
            Book.class.getClassLoader(), Model.class.getInterfaces(),
            new ProxyModel());

        assertTrue(book.toXML().equals("this is xml"));
    }

    @Test
    public void serializeTest() {
        DataManagerInterface dataManager = (DataManagerInterface) Proxy.newProxyInstance(
                DataManager.class.getClassLoader(), DataManager.class.getInterfaces(),
                new ProxyDataManager());

        Set<Object> objects = new HashSet();
//        assertTrue(dataManager.uSerialize(objects).equals("this is serialize"));
    }


}
