package com.pmf.codejam.web.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.pmf.codejam.util.DataLayerUtil;
import com.pmf.codejam.util.IngredientView;
import com.pmf.codejam.util.JQGridObject;
import com.pmf.codejam.util.ProductView;

public class IngredientsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private List<IngredientView> productIngredients;
	private List<IngredientView> ingredientsList;
	private String product;
	private String productId;
	private String quantity;
	private String productToAdd;
	private String ingredientId;
	
	private void fillLists() {
		ingredientsList = DataLayerUtil.getAllIngredients();		
	}
	
	public void ingredientsPerProduct() throws Exception {
		productId = (request.getParameter("productId")+"").trim();
		String sord= (request.getParameter("sord")+"").trim();
		String pageStr= (request.getParameter("page")+"").trim();
		String sidx= (request.getParameter("sidx")+"").trim();
		String _search= (request.getParameter("_search")+"").trim();
		String rowsStr= (request.getParameter("rows")+"").trim();

		if (productId != null && productId.length() > 0) {
			productIngredients = DataLayerUtil.getIngredientsByProductId(new Long(productId).intValue());
		} else {
			productIngredients = DataLayerUtil.getAllIngredients();
		}
		int page = (pageStr != null ? Integer.parseInt(pageStr) : 0);
		int rows = (rowsStr != null ? Integer.parseInt(rowsStr) : 20);;

		Gson gson = new Gson();

		Long total = new Long(productIngredients.size());
		
		JQGridObject object = new JQGridObject();

		object.setPage(page);
		object.setRecords(total);
		object.setHowManyRowsPerView(rows);
		object.setRows(productIngredients);
		String json = gson.toJson(object);
        response.setContentType("application/json");
		response.setContentLength(json.length());
		response.getOutputStream().print(json.toString());
		response.getOutputStream().flush();
		
	}
	public void saveIngredient() throws Exception {
		productToAdd = (request.getParameter("productId")+"").trim();
		ingredientId = (request.getParameter("ingredientName")+"").trim();
		quantity = (request.getParameter("quantity")+"").trim();
		String oper = (request.getParameter("oper")+"").trim();
		
		ingredientId = (ingredientId == null || ingredientId.length() <= 0 ? (request.getParameter("id")+"").trim() : "");
		
		// id
		
		if("add".equalsIgnoreCase(oper)) {
			// TODO validate and insert ingredient
		} else if ("edit".equalsIgnoreCase(oper)) {
			// TODO validate and edit quantity of ingredient
		} else if ("del".equalsIgnoreCase(oper)) {
			// TODO validate and delete ingredient
		} else {
			// invalid operation...
			// TODO
		}
		System.out.println("Product " + productToAdd);
		System.out.println("Ingredient " + ingredientId);
		System.out.println("Quantity " + quantity);
		// Whatever we need to do to save an ingredient as part of a product...
	}
	public String execute() throws Exception {

		String id = (request.getParameter("productId")+"").trim();
		int productId = (id != null && id.length() > 0 ? new Long(id).intValue() : 0);
		productIngredients = DataLayerUtil.getIngredientsByProductId(productId);
		ProductView productView = DataLayerUtil.getProductById(productId);
		product = productView.getProductName();
		productToAdd = productView.getProductId()+ ""; 
		fillLists();
		
		return SUCCESS;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductToAdd() {
		return productToAdd;
	}
	public void setProductToAdd(String productToAdd) {
		this.productToAdd = productToAdd;
	}
	public String getIngredientToAdd() {
		return ingredientId;
	}
	public void setIngredientToAdd(String ingredientToAdd) {
		this.ingredientId = ingredientToAdd;
	}
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}
	public List<IngredientView> getProductIngredients() {
		return productIngredients;
	}
	public void setProductIngredients(List<IngredientView> productIngredients) {
		this.productIngredients = productIngredients;
	}
	public List<IngredientView> getIngredientsList() {
		return ingredientsList;
	}
	public void setIngredientsList(List<IngredientView> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
