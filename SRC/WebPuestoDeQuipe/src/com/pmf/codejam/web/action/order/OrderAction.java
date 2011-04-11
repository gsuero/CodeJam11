package com.pmf.codejam.web.action.order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pmf.codejam.ejb.OrderEntityService;
import com.pmf.codejam.entity.OrderDetail;
import com.pmf.codejam.entity.OrderEntity;
import com.pmf.codejam.entity.Product;
import com.pmf.codejam.util.DataLayerUtil;
import com.pmf.codejam.util.ProductView;
import com.sun.xml.internal.ws.util.StringUtils;

public class OrderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(OrderAction.class.getName());
	
	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	
	private List<ProductView> products;
	private List<ProductView> finalProducts;

	private String product;
	private String bill;
	
	public OrderAction() {
		products = DataLayerUtil.getProducts();
		finalProducts = new ArrayList<ProductView>();		
	}
	public String execute() throws Exception {
		log.info("Order::execute");
		
		return SUCCESS;
	}
	
	
	public String processOrder() throws Exception {
		log.info("Order::processOrder()");
		
		try {
		product = httpServletRequest.getParameter("productsSelected");
		String name = httpServletRequest.getParameter("name");
		String email = httpServletRequest.getParameter("email");
		String phone = httpServletRequest.getParameter("phone");
		String address = httpServletRequest.getParameter("address");
		String bill = httpServletRequest.getParameter("bill");

		
		if(org.apache.commons.lang.xwork.StringUtils.isEmpty(name)) {
			addActionError("Nombre esta vacio o es invalido");
			
		}
		
		if ( org.apache.commons.lang.xwork.StringUtils.isEmpty(phone)) {
			addActionError("Telefono esta vacio o es invalido");
			
		}
		
		if ( org.apache.commons.lang.xwork.StringUtils.isEmpty(address)) {
			addActionError("Direccion esta vacia o es invalida");
		}
		
		if ( org.apache.commons.lang.xwork.StringUtils.isEmpty(product)) {
			addActionError("Factura vacia o invalida.");
		}
		
		if(hasActionErrors()) {
			return ERROR;
		}
		
		//getActionErrors();
				

		StringBuffer input = new StringBuffer(product.trim());
		input.deleteCharAt(0);
		products = new ArrayList<ProductView>();
		
		
		//this values are coming back to null even though we already
		//set them in the execute() method, adding them again
		finalProducts =  getFinalListOfProducts(input.toString());
		Set<OrderDetail> orderDetails = getSetOfOrderDetails(finalProducts);
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCustomerName(name);
		orderEntity.setEmail(email);
		orderEntity.setPhone(phone);
		orderEntity.setAddress(address);
		orderEntity.setOrderDetails(orderDetails);
		
		OrderEntityService orderEntityService = new OrderEntityService();
		orderEntityService.create(orderEntity);
		
		} catch (Exception ex) {
			addActionError("Error al procesar la Orden.");
			log.info("Order::processOrder()::" + ex);
			return ERROR;
		}
		
				
		return SUCCESS;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
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
	
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	private List<ProductView> getFinalListOfProducts (String input) {
		log.info("Order::getFinalListOfProducts() input = " + input );
		
		List<ProductView> result = new ArrayList<ProductView>(); 
		for(String s : input.toString().split("&")) {
			String[] singleProduct = s.split(",");
			for(ProductView productView : getProducts()){
				if((productView.getProductId()
						== Integer.parseInt(singleProduct[0]))
					&& (productView.getQuantity() == 0)) {
					
					productView.setQuantity(Integer.valueOf(singleProduct[1]));
					result.add(productView);
				} else if(productView.getProductId()
						== Integer.parseInt(singleProduct[0])) {
					
					productView.setQuantity(productView.getQuantity()
							+ Integer.valueOf(singleProduct[1]));
				} 
			}
		}
		
		return finalProducts;
	}
	
	private Set<OrderDetail> getSetOfOrderDetails(List<ProductView> listProducts) {
		log.info("Order::getSetOfOrderDetails() input");
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		for(ProductView productView : listProducts) {
			OrderDetail orderDetail = new OrderDetail();
			Product product = new Product();
			
			product.setId(productView.getProductId());
			product.setName(productView.getProductName());
			product.setPrice(productView.getPrice());
			orderDetail.setProduct(product);
			orderDetail.setQuantity(new Integer(productView.getQuantity()).shortValue());
			orderDetails.add(orderDetail);
			
		}
		return orderDetails;
		
	}
	
	
}
