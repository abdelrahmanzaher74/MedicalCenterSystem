/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * //author dell
 */
//Entity
//Table(name = "_Service")
//NamedQueries({
    //NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    //Basic(optional = false)
    //NotNull
    //Column(name = "Service_id")
    private Integer serviceid;
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 50)
    //Column(name = "_Service_Name")
    private String serviceName;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private Collection<Appointment> appointmentCollection;
    //JoinColumn(name = "Equipment_id", referencedColumnName = "Equipment_id")
    //ManyToOne(optional = false)
    private Equipment equipment;
    //JoinColumn(name = "Price_id", referencedColumnName = "Price_id")
    //ManyToOne(optional = false)
    private Prices prices;

    public Service() {
    }

    public Service(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Service(Integer serviceid, String serviceName) {
        this.serviceid = serviceid;
        this.serviceName = serviceName;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceid != null ? serviceid.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceid == null && other.serviceid != null) || (this.serviceid != null && !this.serviceid.equals(other.serviceid))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Service[ serviceid=" + serviceid + " ]";
    }
    
}
