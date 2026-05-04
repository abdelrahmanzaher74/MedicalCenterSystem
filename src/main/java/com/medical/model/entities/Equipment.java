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
import jakarta.validation.constraints.Size;
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
@Table(name = "Equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
    @NamedQuery(name = "Equipment.findByEquipmentid", query = "SELECT e FROM Equipment e WHERE e.equipmentid = :equipmentid"),
    @NamedQuery(name = "Equipment.findByEquipmentName", query = "SELECT e FROM Equipment e WHERE e.equipmentName = :equipmentName"),
    @NamedQuery(name = "Equipment.findByEquipmentModel", query = "SELECT e FROM Equipment e WHERE e.equipmentModel = :equipmentModel"),
    @NamedQuery(name = "Equipment.findByEquipmentPrice", query = "SELECT e FROM Equipment e WHERE e.equipmentPrice = :equipmentPrice"),
    @NamedQuery(name = "Equipment.findByMaintenanceinterval", query = "SELECT e FROM Equipment e WHERE e.maintenanceinterval = :maintenanceinterval")})
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Equipment_id")
    private Integer equipmentid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Equipment_Name")
    private String equipmentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Equipment_Model")
    private String equipmentModel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Equipment_Price")
    private BigDecimal equipmentPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Maintenance_interval")
    private String maintenanceinterval;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipmentid")
    private Collection<Maintenance> maintenanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipmentid")
    private Collection<Service> serviceCollection;

    public Equipment() {
    }

    public Equipment(Integer equipmentid) {
        this.equipmentid = equipmentid;
    }

    public Equipment(Integer equipmentid, String equipmentName, String equipmentModel, BigDecimal equipmentPrice, String maintenanceinterval) {
        this.equipmentid = equipmentid;
        this.equipmentName = equipmentName;
        this.equipmentModel = equipmentModel;
        this.equipmentPrice = equipmentPrice;
        this.maintenanceinterval = maintenanceinterval;
    }

    public Integer getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(Integer equipmentid) {
        this.equipmentid = equipmentid;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public BigDecimal getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(BigDecimal equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
    }

    public String getMaintenanceinterval() {
        return maintenanceinterval;
    }

    public void setMaintenanceinterval(String maintenanceinterval) {
        this.maintenanceinterval = maintenanceinterval;
    }

    @XmlTransient
    public Collection<Maintenance> getMaintenanceCollection() {
        return maintenanceCollection;
    }

    public void setMaintenanceCollection(Collection<Maintenance> maintenanceCollection) {
        this.maintenanceCollection = maintenanceCollection;
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
        hash += (equipmentid != null ? equipmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.equipmentid == null && other.equipmentid != null) || (this.equipmentid != null && !this.equipmentid.equals(other.equipmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Equipment[ equipmentid=" + equipmentid + " ]";
    }
    
}
