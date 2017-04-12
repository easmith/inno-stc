import com.company.Consumer;
import com.company.MyNumber;
import com.company.Producer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eku-win on 12.04.2017.
 */
public class MainTest {

    @Test
    public void putNumberTest() {
        MyNumber monitor = new MyNumber();

        Consumer consumer = new Consumer(monitor);

        // i == 1 чтобы не выводилось сообщение
        consumer.putNumber(1, 1);
        consumer.putNumber(1, 1);

        assertEquals(1, monitor.numbers.size());

        consumer.putNumber(1, 2);
        consumer.putNumber(1, 3);

        assertEquals(3, monitor.numbers.size());

    }
}
