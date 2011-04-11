/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmf.codejam.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @version 09 Abril 2011.
 * @author Frederick
 */
@SuppressWarnings("serial")
@Embeddable
public class IngredientPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID", nullable = false)
    private int productId;
    @Basic(optional = false)
    @Column(name = "INVENTORY_ID", nullable = false)
    private int inventoryId;

    public IngredientPK() {
    }

    public IngredientPK(int productId, int inventoryId) {
        this.productId = productId;
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productId;
        hash += (int) inventoryId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngredientPK)) {
            return false;
        }
        IngredientPK other = (IngredientPK) object;
        if (this.productId != other.productId) {
            return false;
        }
        if (this.inventoryId != other.inventoryId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pmf.codejam.entity.IngredientPK[productId=" + productId + ", inventoryId=" + inventoryId + "]";
    }

}
