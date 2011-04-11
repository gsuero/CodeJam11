package com.pmf.codejam.util;

public class IngredientView {

	private int productId;
	private String ingredientName;
	private int ingredientId;
	private	double quantity;
	
	public IngredientView(String ingredientName, int ingredientId,
			double quantity) {
		super();
		this.ingredientName = ingredientName;
		this.ingredientId = ingredientId;
		this.quantity = quantity;
	}
	
	public IngredientView(int productId, String ingredientName,
			int ingredientId, double quantity) {
		super();
		this.productId = productId;
		this.ingredientName = ingredientName;
		this.ingredientId = ingredientId;
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ingredientId;
		result = prime * result
				+ ((ingredientName == null) ? 0 : ingredientName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientView other = (IngredientView) obj;
		if (ingredientId != other.ingredientId)
			return false;
		if (ingredientName == null) {
			if (other.ingredientName != null)
				return false;
		} else if (!ingredientName.equals(other.ingredientName))
			return false;
		return true;
	}
}
