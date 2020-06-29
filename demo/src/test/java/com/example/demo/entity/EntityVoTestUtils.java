package com.example.demo.entity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class EntityVoTestUtils {
    //实体化数据
    public static final Map<String, Object> STATIC_MAP0 = new HashMap<String, Object>();
    public static final Map<String, Object> STATIC_MAP = new HashMap<String, Object>();
    public static final Map<String, Object> STATIC_MAP2 = new HashMap<String, Object>();

    //忽略的函数方法method
    private static final String NO_NOTICE = "getClass,notify,notifyAll,wait,equals,hashCode,clone";

//    private static final List<Class> CLASS_LIST = new ArrayList<Class>();

    static {
        STATIC_MAP0.put("java.lang.Long", 0L);
        STATIC_MAP0.put("java.lang.String", "tess");
        STATIC_MAP0.put("java.lang.Integer", 0);
        STATIC_MAP0.put("int", 0);
        STATIC_MAP0.put("long", 0);
        STATIC_MAP0.put("java.util.Date", new Date());
        STATIC_MAP0.put("char", '0');
        STATIC_MAP0.put("java.util.Map", new HashMap());
        STATIC_MAP0.put("boolean", false);

        STATIC_MAP.put("java.lang.Long", 1L);
        STATIC_MAP.put("java.lang.String", "test");
        STATIC_MAP.put("java.lang.Integer", 1);
        STATIC_MAP.put("int", 1);
        STATIC_MAP.put("long", 1);
        STATIC_MAP.put("java.util.Date", new Date());
        STATIC_MAP.put("char", '1');
        STATIC_MAP.put("java.util.Map", new HashMap());
        STATIC_MAP.put("boolean", true);

//        CLASS_LIST.add(.class);
//        CLASS_LIST.add(.class);
        STATIC_MAP2.put("java.lang.Long", 2L);
        STATIC_MAP2.put("java.lang.String", "test2");
        STATIC_MAP2.put("java.lang.Integer", 2);
        STATIC_MAP2.put("int", 2);
        STATIC_MAP2.put("long", 2);
        STATIC_MAP2.put("java.util.Date", new Date());
        STATIC_MAP2.put("char", '2');
        STATIC_MAP2.put("java.util.Map", new HashMap());
        STATIC_MAP2.put("boolean", false);
    }


    /**
     * @param CLASS_LIST 类列表
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void justRun(List<Class> CLASS_LIST)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Class temp : CLASS_LIST) {
            Object tempInstance = new Object();
            //执行构造函数
            Constructor[] constructors = temp.getConstructors();
            for (Constructor constructor : constructors) {
                final Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 0) {
                    tempInstance = constructor.newInstance();
                } else {
                    Object[] objects = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        objects[i] = STATIC_MAP.get(parameterTypes[i].getName());
                    }
                    tempInstance = constructor.newInstance(objects);
                }
            }

            //执行函数方法
            Method[] methods = temp.getMethods();
            for (final Method method : methods) {
                if (NO_NOTICE.contains(method.getName())) {
                    break;
                }
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 0) {
                    Object[] objects = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        objects[i] = STATIC_MAP.get(parameterTypes[i].getName());
                    }
                    method.invoke(tempInstance, objects);
                } else {
                    method.invoke(tempInstance);
                }
            }
            //输出执行完的类名
//            System.out.println(temp.getName());
        }
    }
}
