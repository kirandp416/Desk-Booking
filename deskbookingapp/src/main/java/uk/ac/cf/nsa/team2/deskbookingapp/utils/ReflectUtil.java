package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/5
 */
public class ReflectUtil {
    public static <T> T mapToBean(Map<String, Object> source, Class<T> clazz) {
        // create hashmap and lineToHump to key from the database
        HashMap<String, Object> map = new HashMap<>();
        source.forEach((k, v) -> map.put(StringUtil.lineToHump(k), v));
        try {
            T obj = clazz.newInstance();
            // A field that gets all the declarations for a class
            Field[] fields = clazz.getDeclaredFields();
            // for
            for (Field field : fields) {
                // get field name
                String fieldName = field.getName();
                // takes the name of a simple property, and method names for reading and writing the property.
                PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
                // get write method
                Method writeMethod = descriptor.getWriteMethod();
                // solve illegal argument exception
                if (Objects.nonNull(map.get(fieldName))) {
                    writeMethod.invoke(obj, map.get(fieldName));
                }
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
