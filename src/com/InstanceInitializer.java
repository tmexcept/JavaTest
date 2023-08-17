package com;

/**
 * 注意
 */
public class InstanceInitializer {
    //todo 注意，实例化对象的位置不同，打印的结果就不同，
    public static void main(String[] args) {
        staticFunction();
    }
    int a = 110;    // 实例变量
    static int b = 112;     // 静态变量

    static InstanceInitializer st = new InstanceInitializer();
    static {   //静态代码块
        System.out.println("1");
    }
    {       // 实例代码块
        System.out.println("2");
    }

    InstanceInitializer() {    // 实例构造器
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {   // 静态方法
        System.out.println("4");
    }

}
