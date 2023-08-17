package com;

import java.io.File;

public class ChangeFileName {

    public static void main(String[] args){
        String dir = "D:\\手机压缩\\拍摄2\\xiaowan";
        File file = new File(dir);

        if(!file.exists()){
            System.out.println("没有找到文件夹");
            return;
        }

        File[] files = file.listFiles();
        String name;
        for (File f : files) {
            name = f.getName();
            if(name.contains("_batch")){
                //Screenrecorder-2019-12-11-18-40-25-16_batch
                name = name.replace("_batch", "");
                f.renameTo(new File(dir, name));
                System.out.println(name);
            }
        }
    }
}
