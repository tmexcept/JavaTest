package com;

import java.io.*;

public class TestSerializable {
    static class People implements Serializable{

        private Long id;

        public People(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "People{" + "id=" + id +  '}';
        }
    }
    static class Worker extends People implements Serializable {

        public String name;
        public Integer age;

        public Worker(Long id, String name, Integer age) {
            super(id);
            this.name = name;
            this.age = age;
        }

    }
    /**
     * <h1>序列化和反序列化 People 对象</h1>
     */
    private static void testSerializablePeople() throws Exception {

        // 序列化的步骤

        // 用于存储序列化的文件，这里的java_下划线仅仅为了说明是java序列化对象，没有任何其他含义
        File file = new File("D:/people_10.javabean");
        if (!file.exists()) {
            // 1，先得到文件的上级目录，并创建上级目录
            file.getParentFile().mkdirs();
            try {
                // 2，再创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        People p = new People(10L);
        System.out.println("origin is: "+p);

        // 创建一个输出流
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        // 输出可序列化对象
        oos.writeObject(p);
        // 关闭输出流
        oos.close();

        // 反序列化的步骤

        // 创建一个输入流
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file)
        );
        // 得到反序列化的对象，这里可以强转为People类型
        Object newPerson = ois.readObject();
        // 关闭输入流
        ois.close();

        System.out.println(newPerson);
    }

    public static void main(String[] args) throws Exception {
        testSerializablePeople();
        testSerizableWorker();
    }
    /**
     * <h2>子类实现序列化, 父类不实现序列化</h2>
     * */
    private static void testSerizableWorker() throws Exception {

        File file = new File("D:/worker_10.javabean");
        if (!file.exists()) {
            // 1，先得到文件的上级目录，并创建上级目录
            file.getParentFile().mkdirs();
            try {
                // 2，再创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Worker p = new Worker(10L, "lcy", 18);

        // 创建一个输出流
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        // 输出可序列化对象
        oos.writeObject(p);
        // 关闭输出流
        oos.close();

//        p.name = "lcy reset";
//        oos = new ObjectOutputStream(
//                new FileOutputStream(file)
//        );
//        // 输出可序列化对象
//        oos.writeObject(p);
//        // 关闭输出流
//        oos.close();
//
//        p.name = "lcy reset twice";
//        oos = new ObjectOutputStream(
//                new FileOutputStream(file)
//        );
//        // 输出可序列化对象
//        oos.writeObject(p);
//        // 关闭输出流
//        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object newWorker = ois.readObject(); // 父类没有序列化的时候，需要调用父类的无参数构造方法
//        Object newWorker2 = ois.readObject(); // 父类没有序列化的时候，需要调用父类的无参数构造方法
        ois.close();
        System.out.println(newWorker);
        if(newWorker instanceof Worker) {
            Worker worker = ((Worker) newWorker);
            System.out.println("worker is: "+worker.name+"  "+worker.age);
//            Worker worker2 = ((Worker) newWorker2);
//            System.out.println("worker2 is: "+worker2.name+"  "+worker2.age);
        } else {
            System.out.println("newWorker is not instanceof Worker");
        }
    }
}
