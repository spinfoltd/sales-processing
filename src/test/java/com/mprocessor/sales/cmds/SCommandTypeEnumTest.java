package com.mprocessor.sales.cmds;

import org.junit.Assert;
import org.junit.Test;

public class SCommandTypeEnumTest {

    @Test
    public void testEnumVal(){
        Assert.assertTrue(SCommandTypeEnum.ADDCommandType == SCommandTypeEnum.getTypeFromName("add"));
        Assert.assertTrue(SCommandTypeEnum.SubtractCommandType == SCommandTypeEnum.getTypeFromName("subtract"));
        Assert.assertTrue(SCommandTypeEnum.MultiplyCommandType == SCommandTypeEnum.getTypeFromName("multiply"));
        Assert.assertFalse(SCommandTypeEnum.MultiplyCommandType == SCommandTypeEnum.getTypeFromName("multiply2"));
    }
}
