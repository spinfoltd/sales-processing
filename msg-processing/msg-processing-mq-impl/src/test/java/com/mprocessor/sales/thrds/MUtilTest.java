package com.mprocessor.sales.thrds;

import org.junit.Test;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class MUtilTest {
    @Test
    public void testQReverse(){
        Deque<Integer> saleHistory = new ConcurrentLinkedDeque<>();
        saleHistory.add(1);
        saleHistory.add(2);
        saleHistory.add(3);
       /* Integer[] allIInts = saleHistory.toArray(new Integer[saleHistory.size()]);
        Collections.reverse(Arrays.asList(allIInts));*/
        saleHistory.stream().forEach(
                i -> System.out.println(i)
        );
        List<Integer> historyList = saleHistory.stream().collect(Collectors.toList());
        Collections.reverse(historyList);
        historyList.stream().forEach(
                i -> System.out.println(i)
        );
    }
}
