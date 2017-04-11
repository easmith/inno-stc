package proxy;

import com.company.library.models.Book;

import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * Created by eku-win on 11.04.2017.
 */
public class Main {

    public static void main(String[] args) {
//        ProxyCollection proxy = new ProxyCollection();
        Set<Book> catalog = (Set<Book>) Proxy.newProxyInstance(
                MySet.class.getClassLoader(), MySet.class.getInterfaces(),
                new ProxyCollection());

        System.out.println(catalog.contains(0));

    }
}
