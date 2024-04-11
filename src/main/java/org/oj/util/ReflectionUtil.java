package org.oj.util;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;

/**
 * 反射工具类
 *
 * @author TPureZY
 * @create 2021/7/9 13:39
 */
@Slf4j
public class ReflectionUtil {

    public static String entityPackage = "com.spms.entity.";

    /**
     * @param clazz Class类
     * @param flag  0:获取简洁类名, 1:获取完整类名(带路径)
     * @return java.lang.String
     */
    public static String getClassName(Class<?> clazz, int flag) {
        switch (flag) {
            case 0:
                return clazz.getSimpleName();
            case 1:
                return clazz.getName();
            default:
                return "Error";
        }
    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object 对象
     * @param field  属性
     * @return Object 属性值
     */
    public static Object getFieldValue(Object object, Field field) {
        makeAccessible(field);
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            log.error("[{}]中[{}]属性获取错误", object, field.getName(), e);
            return null;
        }
        return result;
    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object    对象
     * @param fieldName 属性名称
     * @return Object 属性值
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            log.error("[{}]中未找到属性[{}]", object, fieldName);
            return null;
        }
        makeAccessible(field);
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            log.error("[{}]中[{}]属性获取错误", object, fieldName, e);
            return null;
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object    对象
     * @param fieldName 属性名称
     * @param value     属性值
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            log.error("[{}]中未找到属性[{}]", object, fieldName);
            return;
        }
        makeAccessible(field);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            log.error("[{}]中[{}]属性设置错误", object, fieldName, e);
        }
    }


    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     *
     * @param clazz 类型
     * @param index 序号
     * @return 泛型参数类型
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class<?>) params[index];
    }

    /**
     * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     *
     * @param <T>   泛型
     * @param clazz 类型
     * @return Class<T> 泛型类型
     */
    public static <T> Class<?> getSuperGenericType(Class<?> clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param object         对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @return Method
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                //Method 不在当前类定义, 继续向上转型
            }
        }

        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param clazz          Class
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @return Method
     */
    public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {

        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                //Method 不在当前类定义, 继续向上转型
            }
        }

        return null;
    }

    /**
     * 使 filed 变为可访问
     *
     * @param field 属性字段
     */
    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object    对象
     * @param filedName 属性字段名
     * @return Field
     */
    public static Field getDeclaredField(Object object, String filedName) {

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                //Field 不在当前类定义, 继续向上转型
            }
        }
        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected)
     *
     * @param object         对象
     * @param methodName     方法
     * @param parameterTypes 参数类型
     * @param parameters     参数
     * @return Object
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalArgumentException  IllegalArgumentException
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) throws InvocationTargetException {

        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        if (method == null) {
            throw new IllegalArgumentException("[" + object + "]中不存在方法 [" + methodName + "]");
        }

        method.setAccessible(true);

        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException e) {
            log.error(e.toString(), e);
        }

        return null;
    }

    /**
     * 根据实体类名获取Class
     *
     * @param className 类名，可以是多级属性，如：student.claz.school
     * @return Class<?>
     */
    public static Class<?> getEntityClass(String className) {
        String[] names = className.split("\\.");
        Class<?> clazz = null;
        for (String name : names) {
            try {
                clazz = Class.forName(entityPackage + camelToPascal(name));
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
        return clazz;
    }

    /**
     * 判断类中字段类型是否为指定类型
     *
     * @param clazz     class
     * @param fieldName 属性字段名称
     * @param fieldType 目标类型
     * @return boolean
     */
    public static boolean fieldTypeEquals(Class<?> clazz, String fieldName, Type fieldType) {
        return getFieldType(clazz, fieldName) == fieldType;
    }

    /**
     * 获取字段类型
     *
     * @param clazz     class
     * @param fieldName 属性字段名称
     * @return 类型
     */
    public static Type getFieldType(Class<?> clazz, String fieldName) {
        Field field = getField(clazz, fieldName);
        if (field != null) {
            return field.getGenericType();
        }
        return null;
    }

    /**
     * 根据字段名获取字段类型，字段名必须为实体类名引导，可多级，如：student.clazz.shool.name
     *
     * @param fieldName String 实体类名引导的字段名
     * @return Type
     */
    public static Type getEntityFieldType(String fieldName) {
        try {
            Class<?> clazz = getEntityClass(fieldName.substring(0, fieldName.lastIndexOf('.')));
            if (clazz == null) {
                return null;
            }
            return getFieldType(clazz, fieldName.substring(fieldName.lastIndexOf('.') + 1));
        } catch (Exception e) {
            log.error("获取实体字段类型错误：" + e.getMessage(), e.toString());
            return null;
        }
    }

    /**
     * 获取字段
     *
     * @param clazz     class
     * @param fieldName 属性字段名称
     * @return java.lang.reflect.Field 属性字段
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 获取实体类名对应的下划线名(即数据库表名格式)
     *
     * @param clazz class
     * @return String
     */
    public static String pascalToUnderline(Class<?> clazz) {
        String bigCamel = clazz.getSimpleName();
        StringBuilder underline = new StringBuilder();
        for (int i = 0; i < bigCamel.length(); i++) {
            char c = bigCamel.charAt(i);
            if (c <= 90 && c >= 65) {
                if (i != 0) {
                    underline.append("_");
                }
                underline.append(Character.toLowerCase(c));
            } else {
                underline.append(c);
            }
        }
        return underline.toString();
    }

    /**
     * camel或pascal转下划线名
     *
     * @param name String 名称，可带“.”
     * @return String
     */
    public static String pascalToUnderline(String name) {
        StringBuilder underline = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c <= 90 && c >= 65) {
                if (i != 0) {
                    underline.append("_");
                }
                underline.append(Character.toLowerCase(c));
            } else {
                underline.append(c);
            }
        }
        return underline.toString();
    }

    /**
     * 下划线转camel名
     *
     * @param name String 名称，可带“.”
     * @return String
     */
    public static String underlineToCamel(String name) {
        StringBuilder camel = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if(c=='_'){
                i++;
                if(i<name.length()){
                    char nextC=name.charAt(i);
                    camel.append(Character.toUpperCase(nextC));
                }
            }
            else{
                camel.append(c);
            }
        }
        return camel.toString();
    }

    /**
     * Camel命名转Pascal命名
     *
     * @param name String Camel字符串
     * @return String Pascal字符串
     */
    public static String camelToPascal(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
