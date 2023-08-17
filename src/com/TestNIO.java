package com;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.*;

public class TestNIO {
    private static void symmetricScranble(CharBuffer buffer){
        while (buffer.hasRemaining()){
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char [] data = "BuXueWuShu".toCharArray();
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length*2);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        charBuffer.put(data);
        System.out.println(charBuffer.rewind());
        symmetricScranble(charBuffer);
        System.out.println(charBuffer.rewind());
        symmetricScranble(charBuffer);
        System.out.println(charBuffer.rewind());
    }


}
