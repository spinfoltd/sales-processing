package com.mprocessor.sales.poc;

import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8ExTests {

    public static class Person{
        Integer id;
        public Person(Integer id){
            this.id = id;
        }
    }

    @Test
    public void test1(){
        IntStream.rangeClosed(1,100).mapToObj((i) -> new Person(i)).forEach(
                p -> System.out.println("Person ID"+p.id)
        );
        //IntBinaryOpertor
        OptionalInt sum = IntStream.rangeClosed(1,5).reduce((a, b) -> a+b);
        System.out.println(sum.orElse(-1));
    }

    @Test
    public void testCommandPattern(){
        Collectors.toList();
        //IntStream.rangeClosed(1,5).collect(Collectors.toList());
        Integer i = new Integer(0);
    }


}
