/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * //author dell
 */
//Entity
//Table(name = "Maintenance")
//NamedQueries({
    //NamedQuery(name = "Maintenance.findAll", query = "SELECT m FROM Maintenance m")})
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    //Basic(optional = false)
    //NotNull
    //Column(name = "Maintenance_ID")
    private Integer maintenanceID;
    //Basic(optional = false)
    //NotNull
    //Column(name = "Maintenance_Date")
    //Temporal(TemporalType.DATE)
    private Date maintenanceDate;
    // //Max(value=?)  //Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    //Basic(optional = false)
    //NotNull
    //Column(name = "Maintenance_Cost")
    private BigDecimal maintenanceCost;
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 15)
    //Column(name = "Maintenance_Type")
    private String maintenanceType;
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 15)
    //Column(name = "Maintenance_Status")
    private String maintenanceStatus;
    //JoinColumn(name = "Equipment_id", referencedColumnName = "Equipment_id")
    //ManyToOne(optional = false)
    private Equipment equipment;
    //JoinColumn(name = "Company_id", referencedColumnName = "Company_id")
    //ManyToOne(optional = false)
    private Maintenancecompany maintenancecompany;

    public Maintenance() {
    }

    public Maintenance(Integer maintenanceID) {
        this.maintenanceID = maintenanceID;
    }

    public Maintenance(Integer maintenanceID, Date maintenanceDate, BigDecimal maintenanceCost, String maintenanceType, String maintenanceStatus) {
        this.maintenanceID = maintenanceID;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceCost = maintenanceCost;
        this.maintenanceType = maintenanceType;
        this.maintenanceStatus = maintenanceStatus;
    }

    public Integer getMaintenanceID() {
        return maintenanceID;
    }

    public void setMaintenanceID(Integer maintenanceID) {
        this.maintenanceID = maintenanceID;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Maintenancecompany getMaintenancecompany() {
        return maintenancecompany;
    }

    public void setMaintenancecompany(Maintenancecompany maintenancecompany) {
        this.maintenancecompany = maintenancecompany;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (maintenanceID != null ? maintenanceID.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maintenance)) {
            return false;
        }
        Maintenance other = (Maintenance) object;
        if ((this.maintenanceID == null && other.maintenanceID != null) || (this.maintenanceID != null && !this.maintenanceID.equals(other.maintenanceID))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Maintenance[ maintenanceID=" + maintenanceID + " ]";
    }
    
}
