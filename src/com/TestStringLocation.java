package com;

public class TestStringLocation {
    public static void main(String[] args) {
//        String a="Aa";
//        String b="Aa";
//        System.out.println(a==b);
//        String c=new String("Aa");
//        System.out.println(b==c);
//        b.charAt(0);
//        String d=new String("Aa");
//        System.out.println(d==c);
//        System.out.println(b==a);

        //这里使用常量池存在的字符串，构建字符串对象
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        //这里构建一个常量池不存在的字符串对象
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }

}
