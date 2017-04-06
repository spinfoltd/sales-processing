package com.mprocessor.sales.model;

import com.mprocessor.sales.cmds.MMMessageTypeUtil;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Samantha on 25/03/2017.
 */
public class MSaleLambTest {

    @Test
    public void testVal(){

        {
            MMessageType1 msgTyp1 = MMMessageTypeUtil.getProd1MsgType1(1);
            MSale sale1 = MSale.create(msgTyp1, new MProducts());
            Assert.assertTrue(sale1.getIndivSalValue().compareTo(BigDecimal.valueOf(1)) == 0);
            sale1.adjustVal( ((i,v) -> i.add(v)), BigDecimal.valueOf(2));
            Assert.assertTrue(sale1.getIndivSalValue().compareTo(BigDecimal.valueOf(3)) ==0);
        }

    }
}
