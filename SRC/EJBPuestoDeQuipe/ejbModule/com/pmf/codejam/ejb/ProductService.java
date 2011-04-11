package com.pmf.codejam.ejb;
import javax.ejb.*;
import javax.persistence.*;

import com.pmf.codejam.entity.Ingredient;
import com.pmf.codejam.entity.InventoryItem;
import com.pmf.codejam.entity.OrderDetail;
import com.pmf.codejam.entity.Product;
import com.pmf.codejam.exception.*;
import com.pmf.codejam.util.JPAUtil;
import java.util.*;

/**
 * 
 * @author fpimentel
 *
 */
@Stateless(name="ProductService", mappedName="ejb/ProductServiceJNDI")
public class ProductService implements ProductServiceLocal {
	@PersistenceUnit(name="PuestoDeQuipeService")
	public EntityManager em;

    public ProductService() {
    	em = new JPAUtil().getEMF().createEntityManager();
    }
    
    public void create(Product product) {
        if (product.getIngredients() == null) {
            product.setIngredients(new HashSet<Ingredient>());
        }
        if (product.getOrderDetails() == null) {
            product.setOrderDetails(new HashSet<OrderDetail>());
        }
        
        try {
            em.getTransaction().begin();
            //For persisting ingredients relations
            Set<Ingredient> attachedIngs = new HashSet<Ingredient>();
            for (Ingredient ingsToAttach : product.getIngredients()) {
                ingsToAttach = em.getReference(ingsToAttach.getClass(), ingsToAttach.getIngredientPK());
                attachedIngs.add(ingsToAttach);
            }
            product.setIngredients(attachedIngs);
            
            //For persisting orders relations
            Set<OrderDetail> attachedOrderDetails = new HashSet<OrderDetail>();
            for (OrderDetail orderDetailsToAttach : product.getOrderDetails()) {
                orderDetailsToAttach = em.getReference(orderDetailsToAttach.getClass(), orderDetailsToAttach.getOrderDetailPK());
                attachedOrderDetails.add(orderDetailsToAttach);
            }
            product.setOrderDetails(attachedOrderDetails);
            em.persist(product);
            
            //Merging the old and the new ingredients
            for (Ingredient ingredient : product.getIngredients()) {
                Product oldProductOfIngredients = ingredient.getProduct();
                ingredient.setProduct(product);
                ingredient = em.merge(ingredient);
                if (oldProductOfIngredients != null) {
                    oldProductOfIngredients.getIngredients().remove(ingredient);
                    oldProductOfIngredients = em.merge(oldProductOfIngredients);
                }
            }
            //Merging the old and the new OrderDetails
            for (OrderDetail orderDetail : product.getOrderDetails()) {
                Product oldProductOfOrderDetails = orderDetail.getProduct();
                orderDetail.setProduct(product);
                orderDetail = em.merge(orderDetail);
                if (oldProductOfOrderDetails != null) {
                    oldProductOfOrderDetails.getOrderDetails().remove(orderDetail);
                    oldProductOfOrderDetails = em.merge(oldProductOfOrderDetails);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    
    public void edit(Integer productId, String name, String description, double price) throws IllegalOrphanException, ProductException, Exception {
        Product product = em.find(Product.class, productId);
    	try {

	        em.getTransaction().begin();
	        product.setName(name);
	        product.setDescription(description);
	        product.setPrice(price);
	        em.flush();
	        em.getTransaction().commit();
    	}
    	catch(Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new ProductException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    	
    }
    
    public void edit(Product product) throws IllegalOrphanException, ProductException, Exception {
        try {
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Set<Ingredient> ingredientSetOld = persistentProduct.getIngredients();
            Set<Ingredient> newIngredients = product.getIngredients();
            Set<OrderDetail> orderDetailSetOld = persistentProduct.getOrderDetails();
            Set<OrderDetail> orderDetailSetNew = product.getOrderDetails();
            List<String> illegalOrphanMessages = null;
            for (Ingredient ingredientSetOldIngredient : ingredientSetOld) {
                if (!newIngredients.contains(ingredientSetOldIngredient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ingredient " + ingredientSetOldIngredient + " since its product field is not nullable.");
                }
            }
            for (OrderDetail orderDetailSetOldOrderDetail : orderDetailSetOld) {
                if (!orderDetailSetNew.contains(orderDetailSetOldOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailSetOldOrderDetail + " since its product field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Ingredient> newAttachedIngredients = new HashSet<Ingredient>();
            for (Ingredient ingredientSetNewIngredientToAttach : newIngredients) {
                ingredientSetNewIngredientToAttach = em.getReference(ingredientSetNewIngredientToAttach.getClass(), ingredientSetNewIngredientToAttach.getIngredientPK());
                newAttachedIngredients.add(ingredientSetNewIngredientToAttach);
            }
            newIngredients = newAttachedIngredients;
            product.setIngredients(newIngredients);
            Set<OrderDetail> attachedOrderDetailSetNew = new HashSet<OrderDetail>();
            for (OrderDetail orderDetailSetNewOrderDetailToAttach : orderDetailSetNew) {
                orderDetailSetNewOrderDetailToAttach = em.getReference(orderDetailSetNewOrderDetailToAttach.getClass(), orderDetailSetNewOrderDetailToAttach.getOrderDetailPK());
                attachedOrderDetailSetNew.add(orderDetailSetNewOrderDetailToAttach);
            }
            orderDetailSetNew = attachedOrderDetailSetNew;
            product.setOrderDetails(orderDetailSetNew);
            product = em.merge(product);
            for (Ingredient ingredientSetNewIngredient : newIngredients) {
                if (!ingredientSetOld.contains(ingredientSetNewIngredient)) {
                    Product oldProductOfIngredientSetNewIngredient = ingredientSetNewIngredient.getProduct();
                    ingredientSetNewIngredient.setProduct(product);
                    ingredientSetNewIngredient = em.merge(ingredientSetNewIngredient);
                    if (oldProductOfIngredientSetNewIngredient != null && !oldProductOfIngredientSetNewIngredient.equals(product)) {
                        oldProductOfIngredientSetNewIngredient.getIngredients().remove(ingredientSetNewIngredient);
                        oldProductOfIngredientSetNewIngredient = em.merge(oldProductOfIngredientSetNewIngredient);
                    }
                }
            }
            for (OrderDetail orderDetailSetNewOrderDetail : orderDetailSetNew) {
                if (!orderDetailSetOld.contains(orderDetailSetNewOrderDetail)) {
                    Product oldProductOfOrderDetailSetNewOrderDetail = orderDetailSetNewOrderDetail.getProduct();
                    orderDetailSetNewOrderDetail.setProduct(product);
                    orderDetailSetNewOrderDetail = em.merge(orderDetailSetNewOrderDetail);
                    if (oldProductOfOrderDetailSetNewOrderDetail != null && !oldProductOfOrderDetailSetNewOrderDetail.equals(product)) {
                        oldProductOfOrderDetailSetNewOrderDetail.getOrderDetails().remove(orderDetailSetNewOrderDetail);
                        oldProductOfOrderDetailSetNewOrderDetail = em.merge(oldProductOfOrderDetailSetNewOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new ProductException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, ProductException {
        try {
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new ProductException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Ingredient> ingredientSetOrphanCheck = product.getIngredients();
            for (Ingredient ingredientSetOrphanCheckIngredient : ingredientSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the Ingredient " + ingredientSetOrphanCheckIngredient + " in its ingredientSet field has a non-nullable product field.");
            }
            Set<OrderDetail> orderDetailSetOrphanCheck = product.getOrderDetails();
            for (OrderDetail orderDetailSetOrphanCheckOrderDetail : orderDetailSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the OrderDetail " + orderDetailSetOrphanCheckOrderDetail + " in its orderDetailSet field has a non-nullable product field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProducts() {
        return findProducts(true, -1, -1);
    }

    public List<Product> findProducts(int maxResults, int firstResult) {
        return findProducts(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
	private List<Product> findProducts(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createNamedQuery("Product.findAll");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Product findProduct(Integer id) {
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductsCount() {
        try {
            Query q = em.createNamedQuery("Product.count");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    @Override
    public void addIngredient(int productId, int ingredientId, int quantity) throws IngredientException {
		Product product = null;
		Set<Ingredient> ingredients = null;
		Query query = em.createNamedQuery("Product.findById");
        query.setParameter("id", productId);
        List<Product> products = query.getResultList();
        if (products.size() > 0) {
        	product = products.get(0);
        	Query query2 = em.createNamedQuery("InventoryItem.findById");
            query.setParameter("id", ingredientId);
            List<InventoryItem> inventoryItems = query.getResultList();
            InventoryItem inventoryItem = inventoryItems.get(0);
            if (products.size() > 0) {
            	Ingredient newIngredient = new Ingredient();
            	newIngredient.setProduct(product);
            	newIngredient.setQuantityNedded(quantity);
            	newIngredient.setInventoryItem(inventoryItem);
            	product.getIngredients().add(newIngredient);
    	        em.getTransaction().begin();
    	    	em.persist(products);
    	    	em.flush();
    	    	em.getTransaction().commit(); 
            }        		        	 	        	           	          
        }

	}
	@Override
	public boolean updateIngredient(int idIngredient,int idProducto,int quantityNeeded)	throws IngredientException {
		Product product = null;
		Set<Ingredient> ingredients = null;
		Query query = em.createNamedQuery("Product.findById");
        query.setParameter("id", idProducto);
        List<Product> products = query.getResultList();
        if (products.size() > 0) {
        	product = products.get(0);
        	ingredients = (Set<Ingredient>) product.getIngredients();
	        	for (Ingredient  ingredient : ingredients)         	   
	        	 if(ingredient.getInventoryItem().getId() == idIngredient)
	        		 ingredient.setQuantityNedded(quantityNeeded);	        	 	        	           	          	
        }
        product.setIngredients(ingredients);        
    	em.getTransaction().begin();
    	em.persist(product);
        em.flush();
        em.getTransaction().commit();
		return true;
	}
	@Override
	public boolean deleteIngredient(int productId, int ingredientId) throws IngredientException {
		try {
			Product product = null;
			Set<Ingredient> ingredients = null;
			Query query = em.createNamedQuery("Product.findById");
	        query.setParameter("id", productId);
	        List<Product> products = query.getResultList();
	        if (products.size() > 0) {
	        	product = products.get(0);
	        	ingredients = (Set<Ingredient>) product.getIngredients();
		        	for (Ingredient  ingredient : ingredients)         	   
		        	 if(ingredient.getInventoryItem().getId() == ingredientId)
		        		 product.getIngredients().remove(ingredient);		        	 	        	           	          	
	        }	               
	    	em.getTransaction().begin();
	    	em.persist(product);
	        em.flush();
	        em.getTransaction().commit();
			return true;
    	}catch (NullPointerException ex) {
    		     throw new IngredientException ("Algo anda mal, no hay entity manager");
    	} catch (Exception ex) {
    		     throw new IngredientException ("Algo anda mal : " + ex.getMessage());
    		     }   

	}

}