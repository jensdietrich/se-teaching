package nz.ac.vuw.jenz.xmlser;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * A utility to inspect any object.
 * @author jens dietrich
 */
public class Inspector {


    public static void main (String[] args) throws Exception {
        Student student = new Student(42,"John", "Smith");
        inspect(student);
    }

    public static void inspect(Object obj) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property:properties) {
            System.out.print(property.getName());
            Method getter = property.getReadMethod();
            Object value = getter.invoke(obj,new Object[]{});
            System.out.print(" = ");
            System.out.println(value);
        }
    }

}
