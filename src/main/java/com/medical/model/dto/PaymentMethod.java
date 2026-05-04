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
//Table(name = "Payment_Method")
//NamedQueries({
    //NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p")})
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    //Basic(optional = false)
    //NotNull
    //Column(name = "Method_id")
    private Integer methodid;
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 30)
    //Column(name = "Method_Name")
    private String methodName;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethod")
    private Collection<Appointment> appointmentCollection;

    public PaymentMethod() {
    }

    public PaymentMethod(Integer methodid) {
        this.methodid = methodid;
    }

    public PaymentMethod(Integer methodid, String methodName) {
        this.methodid = methodid;
        this.methodName = methodName;
    }

    public Integer getMethodid() {
        return methodid;
    }

    public void setMethodid(Integer methodid) {
        this.methodid = methodid;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (methodid != null ? methodid.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.methodid == null && other.methodid != null) || (this.methodid != null && !this.methodid.equals(other.methodid))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.PaymentMethod[ methodid=" + methodid + " ]";
    }
    
}
