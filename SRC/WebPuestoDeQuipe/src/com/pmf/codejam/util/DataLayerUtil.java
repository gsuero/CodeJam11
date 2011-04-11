package com.pmf.codejam.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.pmf.codejam.adapter.social.FacebookAdapter;
import com.pmf.codejam.adapter.social.TwitterAdapter;
import com.pmf.codejam.ejb.ProductService;
import com.pmf.codejam.ejb.ProductServiceLocal;
import com.pmf.codejam.ejb.SpecialService;
import com.pmf.codejam.ejb.SpecialServiceLocal;
import com.pmf.codejam.entity.Ingredient;
import com.pmf.codejam.entity.Product;
import com.pmf.codejam.entity.Special;
import com.pmf.codejam.exception.IllegalOrphanException;
import com.pmf.codejam.exception.ProductException;
import com.pmf.codejam.exception.SpecialException;
import com.pmf.codejam.exception.SpecialExpiredException;
import com.pmf.codejam.social.SpecialsPublisher;

public class DataLayerUtil {

	public static ProductView getProductById(int productId) {
		return new ProductView(productId, "Quipe", 4.55);
	}
	public static List<ProductView> getProducts() {
		ProductServiceLocal service = new ProductService();
		List<Product> productList = service.findProducts();
		List<ProductView> products = new ArrayList<ProductView>();
		
		Iterator<Product> it = productList.iterator();
		
		while(it.hasNext()) {
			Product pro = it.next();
			products.add(new ProductView(pro.getId(),pro.getName(),pro.getPrice()));
		}
		return products;
	}
	
	// TODO
	public static List<IngredientView> getIngredientsByProductId(int productId) {
		List<IngredientView> ingredients = new ArrayList<IngredientView>();
	
		return ingredients;
	}
	// TODO
	public static List<IngredientView> getAllIngredients() {
		List<IngredientView> ingredients = new ArrayList<IngredientView>();
	
		return ingredients;
	}
	
	public static void addProduct(ProductView pro)
	{
		Product prodEntity = new Product();
		pro.setProductName(pro.getProductName());
		pro.setPrice(pro.getPrice());
		ProductServiceLocal prodService = new ProductService();
		prodService.create(prodEntity);
	}
	
	public static void updateProduct(ProductView pro) throws IllegalOrphanException, Exception {
		try{
			ProductServiceLocal prodService = new ProductService();
			prodService.edit(pro.getProductId(),pro.getProductName(), pro.getDescription(),pro.getPrice());
		}
		catch(ProductException ex){
			throw ex;
		}			
	}
	public static void deleteProduct(ProductView pro) throws ProductException, IllegalOrphanException {
		try{
			ProductServiceLocal proService = new ProductService();
			proService.destroy(pro.getProductId());
		}
		catch(ProductException ex){
			throw ex;
		}
	}
	/**
	 * @author Cuantico
	 */
	public static void addSpecial(SpecialView specialView) {
		
		Special specialEntity = new Special();
		specialEntity.setDescription(specialView.getDescription());
		specialEntity.setSummary(specialView.getSummary());

		if (specialView.getExpirationDate() != null &&  specialView.getExpirationDate().getTime() != null)
			specialEntity.setExpirationDate(specialView.getExpirationDate().getTime());
		
		SpecialServiceLocal specialService = new SpecialService();
		specialService.create(specialEntity);
		
		try {
			SpecialsPublisher publisher = new SpecialsPublisher(specialEntity);
			List<String> soc = specialView.getSocialNetworks();
			
			if (soc != null && soc.size() >0) {
				if (soc.contains(Constants.SOCIAL_FACEBOOK)) {
					publisher.addListener(new FacebookAdapter());
				} else if (soc.contains(Constants.SOCIAL_TWITTER)) {
					publisher.addListener(new TwitterAdapter());
				}
				publisher.notifyListeners();
			}
			
		} catch (SpecialExpiredException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Cuantico
	 * @param special
	 */
	public static void updateSpecial(SpecialView special) {
		SpecialServiceLocal specialService = new SpecialService();
		
		Special specialEntity = specialService.findSpecial(new Long(special.getSpecialId()));
		specialEntity.setDescription(special.getDescription());
		specialEntity.setSummary(special.getSummary());

		if (special.getExpirationDate() != null &&  special.getExpirationDate().getTime() != null)
			specialEntity.setExpirationDate(special.getExpirationDate().getTime());
		
		try {
			specialService.edit(specialEntity);
		} catch (IllegalOrphanException e) {
			e.printStackTrace();
		} catch (SpecialException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteSpecial(Long specialId) {
		SpecialServiceLocal specialService = new SpecialService();
		
		Special specialEntity = specialService.findSpecial(specialId);
		try {
			specialService.destroy(specialEntity.getId());
		} catch (IllegalOrphanException e) {
			e.printStackTrace();
		} catch (SpecialException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
