package com.pmf.codejam.middleware;

public class RequestItem {
	private int supplierProductId;
	private String unit; 
	private int quantity;

	//Setters
	public int getSupplierProductId(){
		return supplierProductId;
	}	
	public void setSupplierProductId(int supplierProductId){
		this.supplierProductId = supplierProductId;
	}	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}	
	public int getQuantity()	{
		return quantity;
	}
	public void setQuantity(int quantity)	{
		this.quantity = quantity;
	}
	
}
