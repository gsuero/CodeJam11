/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmf.codejam.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pmf.codejam.util.EjbConstants;

/**
 *
 * @author Frederick
 */
@Entity
@Table(name = EjbConstants.TABLE_ORDERS, catalog="", schema="app")
@NamedQueries({
    @NamedQuery(name = "OrderEntity.findAll", query = "SELECT o FROM OrderEntity o"),
    @NamedQuery(name = "OrderEntity.findByOrderNo", query = "SELECT o FROM OrderEntity o WHERE o.orderNo = :orderNo"),
    @NamedQuery(name = "OrderEntity.findByEmail", query = "SELECT o FROM OrderEntity o WHERE o.email = :email"),
    @NamedQuery(name = "OrderEntity.findByCustomerName", query = "SELECT o FROM OrderEntity o WHERE o.customerName = :customerName"),
    @NamedQuery(name = "OrderEntity.findByAddress", query = "SELECT o FROM OrderEntity o WHERE o.address = :address"),
    @NamedQuery(name = "OrderEntity.findByPhone", query = "SELECT o FROM OrderEntity o WHERE o.phone = :phone"),
    @NamedQuery(name = "OrderEntity.count", query = "select count(o) from OrderEntity as o")})
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDER_NO", nullable = false)
    private Integer orderNo;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Column(name = "CUSTOMER_NAME", length = 100)
    private String customerName;
    @Basic(optional = false)
    @Column(name = "ADDRESS", nullable = false, length = 200)
    private String address;
    @Column(name = "PHONE", length = 20)
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEntity", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails;
   

    public OrderEntity() {
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<OrderDetail> getOrderDetails() {
        if (orderDetails == null) {
        	orderDetails =  new HashSet<OrderDetail>();
        }
        
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pmf.codejam.entity.OrderEntity[orderNo=" + orderNo + "]";
    }

}
