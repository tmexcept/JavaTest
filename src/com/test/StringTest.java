package com.test;

import kotlintest.MyClass;
import kotlintest.TestClassKt;

import java.util.concurrent.ThreadPoolExecutor;

public class StringTest{
    private String strOrigin;

    public static void main(String[] args){
        StringTest test = new StringTest();

        test.strOrigin = "Akgsdldccda;gjak;jsffffkl;ddddjfkl;agggggggdfsfdfxxxxxxxxxxxxxxxx";

        test.getSameStr(test.strOrigin);


        String aa = new String("string");
        String a = aa.intern();
        String bb = new String("string");
        String b = bb.intern();
        System.out.println(a+" " + b +" " + (a == b));
        System.out.println(aa+" " + bb +" " + (aa == bb)+" " + (aa == a)+" " + ("string" == a));
    }

    private void getSameStr(String strOrigin){
        int start = 0, length = 0, startMax = 0, lengthMax = 0;
        char c = 0;
        if(strOrigin == null || strOrigin.length() == 0) {
            System.out.println("最长重复子字符串长度为0");
            return;
        }
        for(int i=0;i<strOrigin.length();i++){
            if(c == strOrigin.charAt(i)){
                length ++;
            } else {
                if(length > lengthMax) {
                    lengthMax = length;
                    startMax = start;
                }
                c = strOrigin.charAt(i);
                start = i;
                length = 1;
//                System.out.println("最长重="+length+"   "+lengthMax +"   "+start);
            }
        }
//        System.out.println("最长重="+length+"   "+lengthMax +"   "+startMax);
        if(length > lengthMax) {
            lengthMax = length;
            startMax = start;
        }
        System.out.println("最长重复子字符串="+strOrigin.substring(startMax, startMax + lengthMax)+"   "+lengthMax);
    }

}
