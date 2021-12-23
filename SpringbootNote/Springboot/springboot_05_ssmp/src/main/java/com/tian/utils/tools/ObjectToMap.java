package com.tian.utils.tools;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ projectName:    Springboot
 * @ package:        com.tian.utils
 * @ className:      ObjectToMap
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/23 20:11
 * @ version:    1.0
 */
public class ObjectToMap {
    public static Map<String, Object> getObjectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = field.get(obj);
            if (value == null)
                value = "";
            map.put(keyName, value);
        }
        return map;
    }
}
