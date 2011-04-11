
package com.pmf.codejam.entity;
import java.io.Serializable;

import com.pmf.codejam.exception.ProductException;
import com.pmf.codejam.util.EjbConstants;
import javax.persistence.*;
import java.util.*;

/**
*
* @author fpimentel
*/
@Entity
@Table(name = EjbConstants.TABLE_PRODUCTS, catalog="", schema="app")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
		
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Price")
	private double price;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private Set<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails;
	
    public Product(){
    	
    }
    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, float price) {
        this.id = id;
        this.price = price;
    }
	//setter's and getter's
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name= name;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public Set<Ingredient> getIngredients(){
		return this.ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients){
		this.ingredients = ingredients;
	}
    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
	//Customs methods.....
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Product[id=" + id + " Name=" + name +"]";
    }   
	
    public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}
	
	//Behaviors
	public void restock(int productQuantity) throws ProductException {
		//Fausto, simplifiqué un poco tu código. Fred
		try {
		 for (Ingredient ing: this.getIngredients())			 
		     ing.getInventoryItem().
		     	restockQuantity(Math.ceil(productQuantity * ing.getQuantityNeeded()));
		}
		catch( Exception e) {
			throw new ProductException(e);
		}
	}
	/***
	 * Its Calculate the amount of products that can be made according to what exists in Stock.
	 * @return
	 */
	public int availableQuantity() throws ProductException {
		//Aquí hice lo mismo. Fred
		double result = 0.0;
		try {
		 for (Ingredient ing: this.ingredients) {
			 double prodQty = Math.floor(ing.getInventoryItem().getQuantity() / ing.getQuantityNeeded());
			 result = result <= 0 ? prodQty : Math.floor(Math.min(result, prodQty));
		 }
		}
		catch(Exception e) {
			throw new ProductException(e);  
		}
		return (int)result;
	}
		
	public void removeIngredient(Ingredient ingredient){
		this.ingredients.remove(ingredient);
	}
}
