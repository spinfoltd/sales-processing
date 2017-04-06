package com.mprocessor.sales.model;

import com.mprocessor.sales.cmds.MMMessageTypeUtil;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;




public class MMessageType1Test {

	@Test
	public void testMessageType1() {
		MMessageType1 mst1 = new MMessageType1(MMMessageTypeUtil.prod1, new BigDecimal(1));
		Assert.assertNotNull(mst1.getValue().compareTo(BigDecimal.ONE)==0);
		Assert.assertTrue(MMsgType.MSG_TYPE_1 == MMsgType.getMsgType(MMessageType1.class));
		Assert.assertFalse(MMsgType.MSG_TYPE_2 == MMsgType.getMsgType(MMessageType1.class));
		Assert.assertFalse(MMsgType.MSG_TYPE_3 == MMsgType.getMsgType(MMessageType1.class));
		Assert.assertTrue(MMsgType.MSG_TYPE_2 == MMsgType.getMsgType(MMessageType2.class));
		Assert.assertFalse(MMsgType.MSG_TYPE_3 == MMsgType.getMsgType(MMessageType2.class));
	}
}
