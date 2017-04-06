package com.mprocessor.sales.thrds;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadTest {

    public Runnable createThread(){
        Runnable r1 = () ->{
            System.out.println("StartedThread{"
                    +"name:"+Thread.currentThread().getName()
                    +",id:"+Thread.currentThread().getId()
                    +"}"
            );

        };
        return r1;
    }

    public static String getThreadJsonVals(Thread th){
        return "name:"+Thread.currentThread().getName()
                +",id:"+Thread.currentThread().getId()
        ;

    }
    public String encloseInBrac(String str){
        return "{"+str+"}";

    }
    public String getJSonStr(Thread th){
        return encloseInBrac(getThreadJsonVals(Thread.currentThread()));
    }

    @Test
    public void testSingle1(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable r1 = () ->{
                System.out.println("Started-T1"
                        +getJSonStr(Thread.currentThread())
                );
                for(int i=0;i<2; i++){
                    System.out.println("Thrd -T1-To Sleep:"+getJSonStr(Thread.currentThread()));
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thrd -T1- WokeUp:"+getJSonStr(Thread.currentThread()));
                }


        };
        Runnable r2 = () ->{
            System.out.println("StartedThread -T2-"
                    +getJSonStr(Thread.currentThread())
            );
            for(int i=0;i<2; i++){
                System.out.println("Thrd -T2-Goin To Sleep:"+getJSonStr(Thread.currentThread()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thrd -T2-WokeUp:"+getJSonStr(Thread.currentThread()));
            }
        };
        executorService.execute(r1);
        executorService.execute(r2);
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
