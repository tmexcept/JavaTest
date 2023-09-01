package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java中 参数传递是值传递，而不是引用地址传递
 */
public class TestObject {

    public static void main(String[] args) {
        Person person = new Person();
        person.name="default";
        person.age = 10;
        System.out.println(person);
        change(person);
        System.out.println(person);

        List<Person> list = new ArrayList<>(2);
        list.add(person);
        System.out.println("####################");
        System.out.println(Arrays.toString(list.toArray()));
        addPerson(list);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("####################");
    }

    public static void addPerson(List<Person> list){
        Person person = new Person();
        person.name="default";
        person.age = 10;
        list.add(person);
    }

    public static void change(Person p) {
        System.out.println("-----------------");
        System.out.println(p);

        p = new Person();
        p.name = "after change";
        p.age = 15;

        System.out.println(p);
        System.out.println("-----------------");
    }

    static class Person{
        String name;
        int age;

        @Override
        public String toString() {
            return super.toString() + "  [name="+name+", age="+age+"]";
        }
    }
}
