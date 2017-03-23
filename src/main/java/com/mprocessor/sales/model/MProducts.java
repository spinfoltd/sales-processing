package com.mprocessor.sales.model;

import com.mprocessor.sales.util.PrintUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MProducts {

	private Map<String,MProduct> products = new HashMap<>();
	private static MProducts instance = new MProducts();
	
	public MProducts(){
	}
	
	/*public static MProducts getInst(){
		if(null == instance){
			instance = new MProducts();
		}
		return instance;
	}*/
	
	
	public MProduct getProduct(String name){
		return products.values().stream()
				.filter( p -> p.getName().equalsIgnoreCase(name))
				.findAny()
				.orElse(null)
				;
	}

	public MProduct createIfNotExistsAndGet(String name){
		Optional<MProduct> optProduct = products.values().stream()
				.filter( p -> p.getName().equalsIgnoreCase(name))
				.findAny()
				;
		if(optProduct.isPresent()){
			return optProduct.get();
		}else{
			MProduct mProduct = new MProduct(name);
			products.put(name, mProduct);
			return mProduct;
		}
	}
	
	public Map<String, MProduct> getProducts() {
		return products;
	}
	
	
	public Integer getTotalSalesRecorderd(){
		AtomicInteger count = new AtomicInteger(0);
		getProducts().values().stream().forEach(
				p -> {count.set(count.get() + p.sales.size());}
				);
		return count.get();
	}
	
	public String toJSonProductList(int tab,boolean printAdj){
		return products.values().stream().map(s -> PrintUtil.getTabsInNewLine(tab+1)+"["+s.toJSon(tab,printAdj)+"]").collect(Collectors.joining(","));
	}
	
	
	
	public String toJSon(int tab,boolean printAdj) {
		return PrintUtil.getTabsInNewLine(tab)+ "MProducts {" + toJSonProductList(tab,printAdj) + "}";
	}
	
	
	
	
	
	
}
