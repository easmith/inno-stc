package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by eku-win on 11.04.2017.
 */
public class ProxyModel implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String str = "";
        switch (method.getName()) {
            case "toXML":
                str = "this is xml";
                break;
        }
        return str;
    }
}