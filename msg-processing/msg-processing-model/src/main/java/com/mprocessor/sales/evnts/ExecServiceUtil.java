package com.mprocessor.sales.evnts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecServiceUtil {
	
	public static void waitUntilTastFinished(ExecutorService execService){
    	try {
			execService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}
