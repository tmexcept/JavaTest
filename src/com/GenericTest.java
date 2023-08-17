package com;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class GenericTest<T> {

    private T data;
    private Set<String> set = new HashSet<>();
    Map<String, String> map = new HashMap<>();

    public <T> GenericTest<T> isBoolean(List<Boolean> data) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put("你好", "世界");
        System.out.println(map.get("hello"));
        return new GenericTest<>();
    }

    public Integer isBoolean(ArrayList<Integer> num) {//参数为list不可以，因为编译阶段泛型擦除
        return 2;
    }

    //泛型擦除的时候，不会将元数据结构（类，属性，方法（结构）返回值及形参）泛型擦除，故可直接通过反射获取泛型类型
    //获取属性上的泛型类型： field.getGenericType();
    //获取方法结构——形参的泛型类型：   method.getGenericParameterTypes()[0];
    //获取方法结构——返回值的泛型类型： method.getGenericReturnType();
    //查看反编译文件
    public static void main(String[] args) throws NoSuchMethodException {
        //获取Test.class类的class对象
        Class<?> testClass = GenericTest.class;
        //获取类的属性字段
        Field[] declaredField = testClass.getDeclaredFields();
        //暴力解除，可以访问私有变量
        Field.setAccessible(declaredField, true);
        System.out.println("属性名：参数类型：参数泛型类型");
        for (Field field : declaredField) {
            String name = field.getName();
            Class<?> type = field.getType();
            Type genericType = field.getGenericType();
            System.out.println(name + ":" + type + ":" + genericType);
        }
        System.out.println("方法形参的泛型类型");
        Method method = testClass.getMethod("isBoolean", new Class[]{List.class});
        ParameterizedType parameterType = (ParameterizedType) method.getGenericParameterTypes()[0];
        System.out.println(parameterType.getActualTypeArguments()[0]); //获取第一个
        System.out.println("方法返回值的泛型类型");
        ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
        System.out.println(returnType.getActualTypeArguments()[0]);

        System.out.println("测试泛型擦除==============");
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);
        List<Integer> list = new ArrayList<Integer>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        /* Output
        [E]
        [K, V]
        *///getTypeParameters仅返回声明时的泛型参数


        Type type = map.getClass().getGenericSuperclass();
        System.out.println(type.getTypeName());
        ParameterizedType types = (ParameterizedType) type;
        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
        for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
            System.out.println(typeArgument.getTypeName());
        }
        /* Output
        java.lang.String
        java.lang.Integer
        实际返回结果是
        K
        V
        */
    }
}
