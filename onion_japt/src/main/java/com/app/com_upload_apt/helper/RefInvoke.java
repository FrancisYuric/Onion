package com.app.com_upload_apt.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 用于进行反射的相关操作
 */
public final class RefInvoke {
    /**
     * 通过给定的参数类型和就具体的传参值直接构建新类
     *
     * @param className   　类名
     * @param paramTypes  　参数类型数组
     * @param paramValues 　实际传参值
     * @return　新创建的类实例
     */
    public static Object createObject(String className, Class[] paramTypes, Object[] paramValues) {
        try {
            Class r = Class.forName(className);
            return createObject(r, paramTypes, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * create instance directly by class
     *
     * @param clazz       target class
     * @param paramTypes  target constructor param types
     * @param paramValues target constructor param values
     * @return target object
     */
    public static Object createObject(Class clazz, Class[] paramTypes, Object[] paramValues) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor(paramTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * create instance with no param
     *
     * @param className target class name
     * @return new instance result
     */
    public static Object createObject(String className) {
        Class[] paramTypes = new Class[]{};
        Object[] paramValues = new Object[]{};
        try {
            Class r = Class.forName(className);
            return createObject(r, paramTypes, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * create new instance by single param
     *
     * @param className  target class name
     * @param paramType  target method param type
     * @param paramValue target method param value
     * @return create instance result
     */
    public static Object createObject(String className, Class paramType, Object paramValue) {
        Class[] paramTypes = new Class[]{paramType};
        Object[] paramValues = new Object[]{paramValue};
        try {
            Class r = Class.forName(className);
            return createObject(className, paramTypes, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * to invoke a method of a specific object
     *
     * @param obj         target object
     * @param methodName  target method name
     * @param paramTypes  param type array
     * @param paramValues param value array
     * @return method invocation result
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] paramTypes, Object[] paramValues) {
        if (obj == null) {
            return null;
        }
        try {
            //try to invoke a private method
            Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
            //find the specific method in the declared object
            method.setAccessible(true);
            return method.invoke(obj, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * try to invoke static method
     *
     * @param className   target class name
     * @param method_name target static method name
     * @param paramTypes  target static method param types
     * @param paramValues rglt_input_small content value array
     * @return invocation result
     */
    public static Object invokeStaticMethod(String className, String method_name, Class[] paramTypes, Object[] paramValues) {
        try {
            Class obj_class = Class.forName(className);
            Method method = obj_class.getDeclaredMethod(method_name, paramTypes);
            method.setAccessible(true);
            return method.invoke(null, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object invokeStaticMethod(String className, String method_name) {
        return invokeStaticMethod(className, method_name, new Class[]{}, new Object[]{});
    }
    
    /**
     * get and set target field value
     *
     * @param className 　class name
     * @param obj       　target field
     * @param fieldName filed name
     * @return
     */
    public static Object getFieldObject(String className, Object obj, String fieldName) {
        try {
            Class objName = Class.forName(className);
            Field field = objName.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object getFieldObject(Class clazz, Object object, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * set field content value of a target class
     *
     * @param className  target class name
     * @param object     target class instance
     * @param fieldName  target field name
     * @param fieldValue target field value
     */
    public static void setFieldObject(String className, Object object, String fieldName, Object fieldValue) {
        try {
            Class objClass = Class.forName(className);
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setFieldObject(Class clazz, Object object, String fieldName, Object fieldValue) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * getStaticFieldObject
     *
     * @param className target class name
     * @param fieldName target field name
     * @return target field object
     */
    public static Object getStaticFieldObject(String className, String fieldName) {
        return getFieldObject(className, null, fieldName);
    }
    
    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }
    /**
     * get static field object
     *
     * @param className  target class name
     * @param fieldName  target field name
     * @param fieldValue target field value
     */
    public static void setStaticFieldObject(String className, String fieldName, Object fieldValue) {
        setFieldObject(className, null, fieldName, fieldValue);
    }
    
    public static Object getFieldObject(Object object, String fieldName) {
        return getFieldObject(object.getClass(), object, fieldName);
    }
    
    
    public static void setFieldObject(Object object, String fieldName, Object fieldValue) {
        setFieldObject(object.getClass(), object, fieldName, fieldValue
        );
    }
}