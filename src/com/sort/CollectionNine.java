package com.sort;

import java.util.TreeSet;
import java.util.Comparator;
class NumComparator implements Comparator<NameTag> {
    public int compare (NameTag left,NameTag right) {
        return(left.getNumber() - right.getNumber());
    }
}
public class CollectionNine {
    public static void main(String arg[]) {
        new CollectionNine();
    }
    CollectionNine() {
        NumComparator comparator = new NumComparator();
        TreeSet<NameTag> set = new TreeSet<>(comparator);
        set.add(new NameTag("Agamemnon",300));
        set.add(new NameTag("Cato",400));
        set.add(new NameTag("Plato",100));
        set.add(new NameTag("Zeno",200));
        set.add(new NameTag("Archimedes",500));

        for(NameTag tag : set)
            System.out.println(tag.name);
        TreeSet<NameTag2> set2 = new TreeSet<>();
        set2.add(new NameTag2("Agamemnon",300));
        set2.add(new NameTag2("Cato",400));
        set2.add(new NameTag2("Plato",100));
        set2.add(new NameTag2("Zeno",200));
        set2.add(new NameTag2("Archimedes",500));
        for(NameTag2 tag : set2)
            System.out.println(tag.name);
    }
}

class NameTag{
    String name;
    int number;

    public NameTag(String name, int number){
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
class NameTag2 implements Comparable<NameTag2>{
    String name;
    int number;

    public NameTag2(String name, int number){
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(NameTag2 o) {
        return number - o.number;
    }
}