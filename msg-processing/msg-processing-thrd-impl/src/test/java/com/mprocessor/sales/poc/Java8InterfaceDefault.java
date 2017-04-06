package com.mprocessor.sales.poc;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Samantha on 24/03/2017.
 */
public class Java8InterfaceDefault {
    public static interface MOperate{
        public Integer op(Integer t);

        default Integer negate( Integer t){
            java.lang.Integer i = new Integer(0);
            return  (0) - op(t);
        }
    }

    public Integer testOp(MOperate mop, Integer i ){
        return mop.negate(i);
    }

    MOperate mop =  new  MOperate(){
        @Override
        public Integer op(Integer t) {
            return t * 3 ;
        }
    };

    @Test
    public void test2(){
        Integer retVal = testOp((i) -> i*2, 10);
        System.out.println(retVal);
        Assert.assertTrue(testOp((i) -> i*2, 10) == -20);
        Assert.assertTrue(testOp(mop, 10) == -30);
    }

    @Test
    public void test3(){
        Integer retVal = testOp((i) -> i*2, 10);
        System.out.println(retVal);
        Assert.assertTrue(testOp((i) -> i*2, 10) == -20);
        Assert.assertTrue(testOp(mop, 10) == -30);
    }
}
