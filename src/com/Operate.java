package com;

import java.util.LinkedHashMap;
import java.util.Map;

public class Operate {

    public static int b = 10;
    public static final int x = 20;
    int a = 10;
    void fun1(){
        this.a = 20;
    }

    public static void main(String[] args) {
        Operate str = null;

        System.out.println("static int b="+str.x+"  "+str.b);
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.get(1);
        map.get(2);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }
    }
}
