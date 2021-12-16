package nz.ac.vuw.jenz.dynproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * Proof of concept implementation to show how to implement an imutable collection wrapper similar to
 * java.util.Collections::unmodifiableCollection using a dynamic proxy.
 * See project readme for limitations.
 * @author jens dietrich
 */
public class ReadOnlyWrapper {


    public static <T> Collection<T> unmodifiableCollection(final Collection<? extends T> c) {

        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                boolean isMutatingState =
                        methodName.startsWith("add") ||
                        methodName.startsWith("remove") ||
                        methodName.startsWith("retain") ||
                        methodName.startsWith("clear");

                if (isMutatingState) {
                    throw new UnsupportedOperationException();
                }
                else {
                    return method.invoke(c,args);
                }
            }
        };
        return (Collection<T>) Proxy.newProxyInstance(
                ReadOnlyWrapper.class.getClassLoader(),
                new Class[]{Collection.class},
                handler
        );
    };


}
