package com.mprocessor.sales.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mprocessor.sales.cmds.MMMessageTypeUtil;

public class MSaleHistTest {
	
	MProducts mProducts;
	@Before
	public void init(){
		mProducts = new MProducts();
	}
	
	private void assertTotalValIsProductOfIndivValAndOcc(MSale sale){
		Assert.assertTrue(sale.getTotalSaleValue().compareTo(
				sale.getIndivSalValue().multiply(BigDecimal.valueOf(sale.getOccurrence()))
				) == 0);
		
	}
	
	private void assertIndivValAndTotlValIsNotNegative(MSale sale){
		Assert.assertFalse(sale.getIndivSalValue().compareTo(
				BigDecimal.ZERO) < 0);
		Assert.assertFalse(sale.getTotalSaleValue().compareTo(
				BigDecimal.ZERO) < 0);
		
	}
	
	private void assertTotalValAndIndivVal(MSale sale){
		assertIndivValAndTotlValIsNotNegative(sale);
		assertTotalValIsProductOfIndivValAndOcc(sale);
	}

	@Test
	public void testValuesInHistory(){
		
		MMessageType2 msgTyp1 = MMMessageTypeUtil.getProd1MsgType2(2,3);
		Assert.assertTrue(MMsgType.MSG_TYPE_2 ==msgTyp1.getMsgType());
		MSale sale = MSale.create(msgTyp1, mProducts);
		assertTotalValAndIndivVal(sale);
		System.out.println(sale.getTotalSaleValue());
		Assert.assertTrue(BigDecimal.valueOf(6).compareTo(sale.getTotalSaleValue())==0);
		Assert.assertTrue(mProducts.getProducts().size()==1);
		//Add Adjustments
		{
			MMessageType2 msgTyp3_1 = MMMessageTypeUtil.getProd1MsgType3ADD(2, 2);
			MSale.create(msgTyp3_1, mProducts);
			MMessageType2 msgTyp3_2 = MMMessageTypeUtil.getProd1MsgType3ADD(2, 2);
			MSale.create(msgTyp3_1, mProducts);
			Assert.assertTrue(sale.getSaleHistory().size() == 2);
			mProducts.getProducts().values().forEach(
					p -> p.sales.forEach(
							s -> {
									assertTotalValAndIndivVal(s);
									s.getSaleHistory().forEach(
											sh-> assertTotalValAndIndivVal(sh.sale)
											);
							
								}
							)
					);
			
			;
			
		}


	}


}
