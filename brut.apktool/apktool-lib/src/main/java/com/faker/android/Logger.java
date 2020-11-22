package com.faker.android;

public class Logger {
    public static void sendLog(ILogCat logCat,String log){
        if(logCat!=null){
            logCat.callLog(log);
        }else {
            System.out.println(log);
        }
    }
}
