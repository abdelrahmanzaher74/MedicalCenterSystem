/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Prices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p"),
    @NamedQuery(name = "Prices.findByPriceid", query = "SELECT p FROM Prices p WHERE p.priceid = :priceid"),
    @NamedQuery(name = "Prices.findByPriceValue", query = "SELECT p FROM Prices p WHERE p.priceValue = :priceValue")})
public class Prices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price_id")
    private Integer priceid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price_Value")
    private BigDecimal priceValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceid")
    private Collection<Doctors> doctorsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceid")
    private Collection<Service> serviceCollection;

    public Prices() {
    }

    public Prices(Integer priceid) {
        this.priceid = priceid;
    }

    public Prices(Integer priceid, BigDecimal priceValue) {
        this.priceid = priceid;
        this.priceValue = priceValue;
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    @XmlTransient
    public Collection<Doctors> getDoctorsCollection() {
        return doctorsCollection;
    }

    public void setDoctorsCollection(Collection<Doctors> doctorsCollection) {
        this.doctorsCollection = doctorsCollection;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priceid != null ? priceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prices)) {
            return false;
        }
        Prices other = (Prices) object;
        if ((this.priceid == null && other.priceid != null) || (this.priceid != null && !this.priceid.equals(other.priceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Prices[ priceid=" + priceid + " ]";
    }
    
}
