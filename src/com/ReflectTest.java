package com;

import java.lang.reflect.Method;

public class ReflectTest {

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception{
        //正常的调用
        ReflectTest apple = new ReflectTest();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());
        //使用反射调用
        Class clz = Class.forName("com.ReflectTest");
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
//        Constructor appleConstructor = clz.getConstructor();
//        Object appleObj = appleConstructor.newInstance();
        Object appleObj = clz.newInstance();
        setPriceMethod.invoke(appleObj, 14);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("反射对象，发射方法 Apple Price:" + getPriceMethod.invoke(appleObj));

        setPriceMethod.invoke(apple, 25);
        System.out.println("通过已存在的对象，反射方法 Apple Price:" + apple.getPrice());


        int[] s={0, 1, 2, 3, 4};
        int m = 3, n = 5;
        int last = 0;
        int time = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m + time) % n;
            time ++;
        }

        System.out.println("last = "+last);
    }
}
