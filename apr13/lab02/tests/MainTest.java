import org.junit.jupiter.api.Test;
import ru.easmith.Main;
import ru.easmith.jaxbmodels.*;
import ru.easmith.threads.XmlToDbCareThread;
import ru.easmith.utils.DatabaseManager;
import ru.easmith.utils.XmlManager;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eku on 16.04.17.
 */
public class MainTest {
    protected static String userListFileName = "test_userList.xml";

    @Test
    public void marshallingTest() {
        File f = new File("xml/" + userListFileName);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }

        UserList userList = MyObjectFactory.createUserList();
        User user = MyObjectFactory.createUser("login", "name", "password", false);
        userList.getUser().add(user);

        XmlManager.exportObject(userList, userListFileName);

        assertTrue(f.exists());
    }

    @Test
    public void unmarshallingTest() {
        User user = MyObjectFactory.createUser("login", "name", "password", false);
        UserList userList = (UserList)XmlManager.importObject(userListFileName, UserList.class);
        User userFromXml = userList.getUser().get(0);

        assertEquals(user, userFromXml, "User not equal to UserFromXml");
    }

    @Test
    public void clearDBTest() {
        DatabaseManager.getInstance().clearDB();

        try {
            ResultSet resultSet = DatabaseManager.getInstance().executeQuery("SELECT COUNT(*) as total FROM users");
            while (resultSet.next() ) {
                assertEquals(0, resultSet.getInt("total"));
            }

            resultSet = DatabaseManager.getInstance().executeQuery("SELECT COUNT(*) as total FROM tasks");
            while (resultSet.next() ) {
                assertEquals(0, resultSet.getInt("total"));
            }


        } catch (SQLException e) {

        }
    }

    @Test
    public void readDBTest() {
        User user = MyObjectFactory.createUser("login", "name", "password", false);
        user.toDB();

        UserList userList = MyObjectFactory.createUserList();
        userList.fromDB();

        User userFromDB = userList.getUser().get(0);

        assertEquals(user, userFromDB);
    }

    @Test
    public void threadCareTest() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        DatabaseManager.getInstance().clearDB();
        ArrayList<Future> futures = new ArrayList<>();

        Set<String> monitor = new HashSet<>();

        futures.add(threadPool.submit(new XmlToDbCareThread(UserList.class, userListFileName, monitor)));
        Main.waitFuture(futures);

        assertTrue(monitor.contains(UserList.class.getName()));
    }

}
