package com.pmf.codejam.ejb;
import java.util.List;

import com.pmf.codejam.entity.Product;
import com.pmf.codejam.exception.*;

public interface ProductServiceLocal {
	public void create(Product product);
    public void edit(Product product) throws IllegalOrphanException, ProductException, Exception;
    public void edit(Integer id, String name, String description, double price) throws IllegalOrphanException, ProductException, Exception;
    public void destroy(Integer id) throws IllegalOrphanException, ProductException;
    public List<Product> findProducts();
    public List<Product> findProducts(int maxResults, int firstResult);
    public Product findProduct(Integer id);
    public int getProductsCount();
	public void addIngredient(int productId, int ingredientId, int quantity)throws IngredientException;
	public boolean updateIngredient(int idIngredient,int idProducto,int quantityNeeded) throws IngredientException;
	public boolean deleteIngredient(int productId, int ingredientId) throws IngredientException;	
}
