package com.pmf.codejam.web.action.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.pmf.codejam.util.BusinessValidator;
import com.pmf.codejam.util.DataLayerUtil;
import com.pmf.codejam.util.IngredientView;
import com.pmf.codejam.util.JQGridObject;
import com.pmf.codejam.util.ProductView;

public class ProductsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ProductsAction.class.getName());
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private List<ProductView> products;
	private String product;
	
	public String execute() throws Exception {

		return SUCCESS;
	}
	public String saveProduct() throws Exception {

		try {
			String operation = request.getParameter("operation");
			if (operation== null || operation.length() < 1)
				operation = request.getParameter("oper") + "";

			String productId = (request.getParameter("id") + "").trim();
			String price = request.getParameter("price");
			String productName = (request.getParameter("productName") + "").trim();
			ProductView product = new ProductView(Integer.parseInt(productId), productName, Double.parseDouble(price));

			if(!(BusinessValidator.isNumber(productId)||
				 BusinessValidator.isValidProductName(productName)|| 
				 BusinessValidator.isNumber(price))){
					return ERROR;
			}
			if ("edit".equalsIgnoreCase(operation) || "update".equalsIgnoreCase(operation)) {				
				DataLayerUtil.updateProduct(product);				
			}  else if ("delete".equalsIgnoreCase(operation)) {
				DataLayerUtil.deleteProduct(product);				
			}  else if ("add".equalsIgnoreCase(operation)) {				
				DataLayerUtil.addProduct(product);
			}  else {
				throw new Exception("No se especifico ninguna operacion...");
			}
		} catch (NumberFormatException ex) {
			addActionError("Informacion invalida...");
			return ERROR;
		} catch (NullPointerException ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}
	public void products() throws Exception {

		String catid= (request.getParameter("catid")+"").trim();
		String sord= (request.getParameter("sord")+"").trim();
		String pageStr= (request.getParameter("page")+"").trim();
		String sidx= (request.getParameter("sidx")+"").trim();
		String _search= (request.getParameter("_search")+"").trim();
		String rowsStr= (request.getParameter("rows")+"").trim();

		products = DataLayerUtil.getProducts();
		
		int page = (pageStr != null ? Integer.parseInt(pageStr) : 0);
		int rows = (rowsStr != null ? Integer.parseInt(rowsStr) : 20);;

		Gson gson = new Gson();

		Long total = new Long(products.size());
		
		JQGridObject object = new JQGridObject();

		object.setPage(page);
		object.setRecords(total);
		object.setHowManyRowsPerView(rows);
		object.setRows(getProducts());
		String json = gson.toJson(object);
        response.setContentType("application/json");
		response.setContentLength(json.length());
		response.getOutputStream().print(json.toString());
		response.getOutputStream().flush();
	}
	
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}


	public List<ProductView> getProducts() {
		return products;
	}


	public void setProducts(List<ProductView> products) {
		this.products = products;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}
}
