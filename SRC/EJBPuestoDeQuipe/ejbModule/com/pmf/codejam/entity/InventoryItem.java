package com.pmf.codejam.entity;
import java.io.Serializable;
import java.util.Set;

import com.pmf.codejam.util.EjbConstants;
import javax.persistence.*;

/**
*
* @author fpimentel
*/
@Entity
@Table(name = EjbConstants.TABLE_INVENTORY)
@NamedQueries({
    @NamedQuery(name = "InventoryItem.findAll", query = "SELECT i FROM InventoryItem i"),
    @NamedQuery(name = "InventoryItem.findById", query = "SELECT i FROM InventoryItem i WHERE i.id = :id"),
    @NamedQuery(name = "InventoryItem.findByDescription", query = "SELECT i FROM InventoryItem i WHERE i.description = :description"),
    @NamedQuery(name = "InventoryItem.findByQuantity", query = "SELECT i FROM InventoryItem i WHERE i.description = :quantity"),
    @NamedQuery(name = "InventoryItem.findByRestockingLevel", query = "SELECT i FROM InventoryItem i WHERE i.restockingLevel = :restockingLevel"),
    @NamedQuery(name = "InventoryItem.findByRestockingQuantity", query = "SELECT i FROM InventoryItem i WHERE i.restockingQuantity = :restockingQuantity")})
public class InventoryItem implements Serializable{
	private static final long serialVersionUID = 1L; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)	
    @Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name="SUPPLIER_PRODUCT_ID")
	private String supplierProductId;
	
	@Column(name="DESCRIPTION")
	private String description;

	@Basic(optional = false)
	@Column(name="QUANTITY")
	private double quantity;

	@Basic(optional = false)
	@Column(name="UNIT")
	private String unit; 
	

	@Basic(optional = false)
    @Column(name = "RESTOCKING_LEVEL", nullable = false)
	private double  restockingLevel;

    @Basic(optional = false)
    @Column(name = "RESTOCKING_QUANTITY", nullable = false)
	private double  restockingQuantity;	
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryItem", fetch = FetchType.LAZY)
	@OneToOne          
	private Set<Ingredient> ingredients;
	
    public InventoryItem() {
    }

    public InventoryItem(int id) {
        this.id = id;
    }

    public InventoryItem(int id, String description, double quantity, String unit, double restockingLevel, double restockingQuantity) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.restockingLevel = restockingLevel;
        this.restockingQuantity = restockingQuantity;
    }
    
	//getter's and setter's
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}		
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public double getQuantity() {
		return this.quantity;
	}
	
	public String getDescription() {
		return description;
	}	
	public void setSupplierProductId(String supp){
		this.supplierProductId = supp;
	}	
	public String getSupplierProductId(){
		return supplierProductId;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}	
	public double getRestockingLevel() {
		return restockingLevel;
	}
	public void setRestockingLevel(double restockingLevel) {
		this.restockingLevel = restockingLevel;
	}	
	public double getRestockingQuantity() {
		return restockingQuantity;
	}	
	public void setRestockingQuantity(double restockingQuantity) {
		this.restockingQuantity = restockingQuantity;
	}		
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
	//Customs methods
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryItem)) {
            return false;
        }
        InventoryItem other = (InventoryItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pmf.codejam.entity.InventoryItem[id=" + id + "]";
    }

	/**
	 * Its is responsible for decreasing the amount of ingredient inventory.
	 */
	public void decreaseQuantity(double quantityToDecrease){
		if((this.quantity <= this.restockingLevel) || (this.quantity < quantityToDecrease)){				
			restockQuantity(this.getRestockingQuantity());
			//WHAT WE GONNA DO BEFORE THE SERVICE CALL???.I only know that the amount is increased.
			this.quantity+= quantityToDecrease;
		}
		else{
			this.quantity-=  quantityToDecrease; 
		}
	}	
	/**
	 * Its is responsible for increase the amount of ingredient inventory to calling Supply WebServices.
	 * @param supplierId
	 * @param uni
	 * @param quantity
	 */
	public void restockQuantity(double quantityToRestock)
	{
		//Remove this comment when we implement the web service.
		/*
		InventoryRequest inventoryR = new InventoryRequest();
		RequestItem itemR = new RequestItem();
		itemR.setQuantity(quantity);
		itemR.setUnit = unit;
		itemR.setSupplierProductId(supplierProductId);
		inventoryR.addItem(itemR);
		sendRequest(inventoryR);
		*/	
	}
	
	
}
