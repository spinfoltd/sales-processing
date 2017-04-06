package com.mprocessor.sales.poc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by Samantha on 25/03/2017.
 */
public class Java8FntTests {
    public static class MAccount{

        public MAccount(BigDecimal balance) {
            this.balance = balance;
        }

        BigDecimal balance;

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
    }
    public static class AccountComp implements Comparator<MAccount>{
        @Override
        public int compare(MAccount o1, MAccount o2) {
            return o2.getBalance().compareTo(o1.getBalance());
        }
    }

    @Test
    public void test1(){
        List<MAccount> allAccts = IntStream.rangeClosed(1,5).mapToObj(BigDecimal::valueOf)
                .map(MAccount :: new)
                .collect(Collectors.toList());
        allAccts.forEach(i -> System.out.println(i.getBalance()));
        allAccts.sort(new AccountComp()::compare);
        allAccts.forEach(i -> System.out.println(i.getBalance()));
    }
    public static interface MPrint{
        public void mprint(int sendVal);
    }
    public static class M{
        int val;
        public M(int val){
            this.val = val;
        }
        public static void  printAnyVal(int val){
            System.out.println(val);
        }
        public static void  printNegateVal(int val){
            System.out.println(-val);
        }
        public void acceptMPrint(MPrint m){
            m.mprint(val);
        }
    }

    @Test
    public void test2(){
        M m = new M(20);
        m.acceptMPrint( (i) -> System.out.println(i));
        m.acceptMPrint(System.out::println);
        m.acceptMPrint(M::printAnyVal);
        m.acceptMPrint(M::printNegateVal);
    }

    @Test
    public void testBase1(){
        IntStream.rangeClosed(1,5)
                .mapToObj(BigDecimal::valueOf)
                .map(MAccount :: new)
                .forEach( a -> System.out.println("XXX"+a.getBalance()));
    }
}



