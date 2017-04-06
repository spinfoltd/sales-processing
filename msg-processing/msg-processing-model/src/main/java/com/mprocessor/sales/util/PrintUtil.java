package com.mprocessor.sales.util;

import java.util.stream.IntStream;

public class PrintUtil {
	public static String getTabs(int tab ){
		StringBuilder sb = new StringBuilder();
		IntStream.rangeClosed(1, tab).forEach( 
				(i) ->{
					sb.append("\t");
					});
		return sb.toString();
	}
	public static String getTabsInNewLine(int tab ){
		return "\n"+ getTabs(tab);
	}

}
