package utils.profiling;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by eku on 26.04.17.
 */
public class ProfilingBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = Logger.getLogger(ProfilingBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        Class type = bean.getClass();
        if (type.isAnnotationPresent(ProfilingAnnotation.class)) {
            Object proxy = Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, args);
                    long after = System.nanoTime();


                    System.out.println(type.getCanonicalName() + "::" + method.getName() + " runtime: " + (after - before) + " ns");
//                    LOGGER.info("метод " + method.getName() + " работал: "+(after-before)+" наносекунд");
                    return retVal;
                }
            });
            return proxy;
        } else {
            return bean;
        }
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        return bean;
    }
}