package com.mprocessor.sales.model;

import com.mprocessor.sales.cmds.CommandContainer;
import com.mprocessor.sales.cmds.MMMessageTypeUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class MSaleTest {
	MProducts mProducts;
	@Before
	public void init(){
		mProducts = new MProducts();
	}

	@Test
	public void testSaleFromMsg1() {
		MMessageType1 msgTyp1 = MMMessageTypeUtil.getProd1MsgType1(1);
		MSale sale = MSale.create(msgTyp1, mProducts);
		Assert.assertTrue(BigDecimal.ONE.compareTo(sale.getTotalSaleValue())==0);
		Assert.assertTrue(mProducts.getProducts().size()==1);
		Assert.assertTrue(mProducts.getTotalSalesRecorderd()==1);
	}
	
	@Test
	public void testSaleFromMsg2() {
		MMessageType1 msgTyp1 = MMMessageTypeUtil.getProd1MsgType2(2,3);
		Assert.assertTrue(MMsgType.MSG_TYPE_2 ==msgTyp1.getMsgType());
		MSale sale = MSale.create(msgTyp1, mProducts);
		System.out.println(sale.getTotalSaleValue());
		Assert.assertTrue(BigDecimal.valueOf(6).compareTo(sale.getTotalSaleValue())==0);
		Assert.assertTrue(mProducts.getProducts().size()==1);
	}
	
	@Test
	public void testSaleFromTwoMsgs() {
		MMessageType1 msgTyp1 = MMMessageTypeUtil.getProd1MsgType1(3);
		MMessageType1 msgTyp2 = MMMessageTypeUtil.getProd1MsgType2(2,4);
		Assert.assertTrue(MMsgType.MSG_TYPE_1 ==msgTyp1.getMsgType());
		Assert.assertTrue(MMsgType.MSG_TYPE_2 ==msgTyp2.getMsgType());
		{
			MSale sale1 = MSale.create(msgTyp1, mProducts);
			MSale sale = MSale.create(msgTyp2, mProducts);
			System.out.println(sale.getTotalSaleValue());
			Assert.assertTrue(BigDecimal.valueOf(8).compareTo(sale.getTotalSaleValue())==0);
			Assert.assertTrue(mProducts.getProducts().size()==1);
		}
		{
		MMessageType1 msgTyp2_2 = MMMessageTypeUtil.getProd2MsgType2(2,4);
		
		Assert.assertTrue(MMsgType.MSG_TYPE_2 ==msgTyp2_2.getMsgType());
		{	
			MSale sale = MSale.create(msgTyp2_2, mProducts);
			Assert.assertTrue(BigDecimal.valueOf(8).compareTo(sale.getTotalSaleValue())==0);
			Assert.assertTrue(mProducts.getProducts().size()==2);
		}
		}
		Assert.assertTrue(mProducts.getTotalSalesRecorderd()==3);
	}
	
	@Test
	public void testSaleForMessage3() {
		MMessageType1 msgT3 = MMMessageTypeUtil.getProd1MsgType3ADD(1,2);
		Assert.assertTrue(MMsgType.MSG_TYPE_3 ==msgT3.getMsgType());
		MSale saleT3 = MSale.create(msgT3, mProducts);
		Assert.assertNotNull(mProducts.getProduct(MMMessageTypeUtil.prod1));
		Assert.assertTrue(BigDecimal.valueOf(1).compareTo(saleT3.getTotalSaleValue())==0);
		System.out.println(mProducts.getProducts());
		Assert.assertTrue(mProducts.getProducts().size()==1);
	}
	
	@Test
	public void testSaleForMessage3Complex() {
		{
		MMessageType1 msgT1_1 = MMMessageTypeUtil.getProd1MsgType1(3);
		MMessageType1 msgT3_1 = MMMessageTypeUtil.getProd1MsgType3ADD(1,2);
		Assert.assertTrue(MMsgType.MSG_TYPE_3 ==msgT3_1.getMsgType());
		MSale saleT1_1 = MSale.create(msgT1_1, mProducts);
		Assert.assertTrue(BigDecimal.valueOf(3).compareTo(saleT1_1.getTotalSaleValue())==0);
		Assert.assertTrue(mProducts.getProducts().size()==1);
		MSale sale2 = MSale.create(msgT3_1, mProducts);
		Assert.assertTrue(mProducts.getProducts().size()==1);
		Assert.assertTrue(BigDecimal.valueOf(1).compareTo(sale2.getTotalSaleValue())==0);
		System.out.println(saleT1_1.getTotalSaleValue());
		Assert.assertTrue(BigDecimal.valueOf(5).compareTo(saleT1_1.getTotalSaleValue())==0);
		
		}
	}
	
	@Test
	public void testSaleAdjust() {
		{
		MMessageType1 msgT1_1 = MMMessageTypeUtil.getProd1MsgType1(3);
		MSale saleT1_1 = MSale.create(msgT1_1, mProducts);
		Assert.assertTrue(BigDecimal.valueOf(3).compareTo(saleT1_1.getTotalSaleValue())==0);
		mProducts.getProduct(MMMessageTypeUtil.prod1)
		.adjustSales(CommandContainer.getInst().getAddCommand(), BigDecimal.valueOf(2),MMMessageTypeUtil.getProd1MsgType3ADD(2, 4));
		Assert.assertTrue(BigDecimal.valueOf(5).compareTo(saleT1_1.getTotalSaleValue())==0);
		}
	}

}
