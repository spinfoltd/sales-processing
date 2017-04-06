package com.mprocessor.sales.cmds;

import org.junit.Assert;
import org.junit.Test;

public class BaseISCommandTest {
    @Test
    public void testAll(){
            Assert.assertTrue(AddISCommand.class == BaseISCommand.getCommand("add").getClass());
            Assert.assertTrue(SubISCommand.class == BaseISCommand.getCommand("subtract").getClass());
            Assert.assertTrue(MultISCommand.class == BaseISCommand.getCommand("multiply").getClass());
            Assert.assertNull(BaseISCommand.getCommand("mult1"));
    }
}
