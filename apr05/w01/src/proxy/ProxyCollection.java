package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by eku-win on 11.04.2017.
 */
public class ProxyCollection implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(proxy);
//        return method.invoke(proxy, args);
        switch (method.getName()) {
            case "add" :
                return Boolean.TRUE;
            case "remove" :
                return Boolean.TRUE;
            case "contains" :
                boolean[] mas = {true, false, true, true};
                Integer index = (Integer) args[0];
                if (index >= mas.length) {
                    return Boolean.FALSE;
                }
                return mas[index];
        }
        return Boolean.TRUE;
    }
}
