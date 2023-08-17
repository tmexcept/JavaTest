package com.test;

import java.util.Calendar;
import java.util.Date;

public class ObjectTest {
    static ObjectTest objectTest = new ObjectTest();//此处如果不加static会造成栈溢出
    ObjectTest o1;
    static ObjectTest o3;

    public ObjectTest(){
//        o1 = new ObjectTest();//栈溢出
//        ObjectTest o2 = new ObjectTest();//栈溢出
//        o3 = new ObjectTest();//栈溢出
    }

    public static void main(String[] args) {
        ObjectTest o = new ObjectTest();

        RxBusMaskPartyTime partyTime = new RxBusMaskPartyTime();
        o.rxBusMaskPartyTime(partyTime);
    }


    public void rxBusMaskPartyTime(RxBusMaskPartyTime tipsEntity){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        hour = 18;
        minute = 20;
        tipsEntity.fromHour = 18;
        tipsEntity.toHour = 20;
        tipsEntity.fromM = 0;
        tipsEntity.toM = 30;

        if(tipsEntity.fromHour == tipsEntity.toHour) {
            if(tipsEntity.toM > tipsEntity.fromM) {
                int dismissGradientFindDelayMinute = (tipsEntity.toM - tipsEntity.fromM) * 60;
                System.out.println("结束倒计时时间是  "+dismissGradientFindDelayMinute);

            }
        } else if(tipsEntity.fromHour > tipsEntity.toHour) {//隔天
            System.out.println("MainActivity  假面Party开始时间 在午夜 tipsEntity.fromHour="+tipsEntity.fromHour
                    +"  "+tipsEntity.toHour);
            if (hour >= tipsEntity.fromHour) {
                if(minute >= tipsEntity.fromM) {
                    int dismissGradientFindDelayMinute = (24 + tipsEntity.toHour - hour) * 60
                            +tipsEntity.toM - minute;
                    System.out.println("11 大于 结束倒计时时间是  "+dismissGradientFindDelayMinute);
                } else if(minute < tipsEntity.fromM) {
                    int dismissGradientFindDelayMinute = tipsEntity.fromM - minute;
                    System.out.println("11 大于 开始倒计时时间是  "+dismissGradientFindDelayMinute);
                }

            } else if(hour <= tipsEntity.toHour){
                int dismissGradientFindDelayMinute = (tipsEntity.toHour - hour) * 60
                        +tipsEntity.toM - minute;
                System.out.println("11 大于 结束倒计时时间是  "+dismissGradientFindDelayMinute);

            } else if(hour < tipsEntity.fromHour){
                int showGradientFindDelayMinute = (tipsEntity.fromHour* 60 + tipsEntity.fromM - hour * 60 - minute);
                System.out.println("大于显示倒计时时间是  "+showGradientFindDelayMinute);
            }
        } else {
            System.out.println("MainActivity  假面Party开始时间 在今天 tipsEntity.fromHour="+tipsEntity.fromHour+"  "+tipsEntity.toHour);
            if(hour == tipsEntity.fromHour && minute < tipsEntity.fromM) {
                int showGradientFindDelayMinute = tipsEntity.fromM - minute;
                System.out.println("11 小于显示倒计时时间是  "+showGradientFindDelayMinute);
            } else if (hour >= tipsEntity.fromHour && hour <= tipsEntity.toHour) {
                int showGradientFindDelayMinute = (tipsEntity.toHour - hour) * 60
                        +tipsEntity.toM - minute;
                System.out.println("22 小于 消失 倒计时时间是  "+showGradientFindDelayMinute);
            }
        }
    }
    public static class RxBusMaskPartyTime {
        public int fromHour;
        public int fromM;
        public int toHour;
        public int toM;
    }

    public static String transferSeconds2PeriodTime(long totalSeconds) {
        totalSeconds=totalSeconds/1000;
        long days, hours, minutes, seconds;

        days = totalSeconds / 86400;
        hours = totalSeconds % 86400 / 3600;
        minutes = totalSeconds % 86400 % 3600 / 60;
        seconds = totalSeconds % 86400 % 3600 % 60;
        String result = "";

        if (days > 0)
            result = days + ":";
        if (hours > 0){
            if(hours<10){
                result +=  "0"+hours + ":";
            }else{
                result += hours + ":";
            }
        }
        if (minutes >=0){
            if(minutes<10){
                result += "0"+minutes + ":";
            }else{
                result += minutes + ":";
            }
        }
        if (seconds >= 0){
            if(seconds<10){
                result += "0"+seconds ;
            }else{
                result += seconds ;
            }
        }
        return result;
    }

    public static void getCalendar(){
        //获取当前时间
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println(year + "/" + (month+1) + "/" + date + " " +hour + ":" +minute + ":" + second);

    }
}
