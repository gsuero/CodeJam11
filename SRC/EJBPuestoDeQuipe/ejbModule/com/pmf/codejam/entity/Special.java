/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmf.codejam.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Frederick
 */
@Entity
@Table(name = "SPECIALS", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Special.findAll", query = "SELECT s FROM Special s"),
    @NamedQuery(name = "Special.findById", query = "SELECT s FROM Special s WHERE s.id = :id"),
    @NamedQuery(name = "Special.findByDescription", query = "SELECT s FROM Special s WHERE s.description = :description"),
    @NamedQuery(name = "Special.findBySummary", query = "SELECT s FROM Special s WHERE s.summary = :summary"),
    @NamedQuery(name = "Special.findByCreationDate", query = "SELECT s FROM Special s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "Special.findByExpirationDate", query = "SELECT s FROM Special s WHERE s.expirationDate = :expirationDate")})
public class Special implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SUMMARY")
    private String summary;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    public Special() {
    }

    public Special(Long id) {
        this.id = id;
    }

    public Special(Long id, String description, Date creationDate) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Special)) {
            return false;
        }
        Special other = (Special) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pmf.codejam.entity.Special[id=" + id + "]";
    }

}