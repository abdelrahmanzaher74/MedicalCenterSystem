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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * //author dell
 */
//Entity
//Table(name = "Patient_phone")
//NamedQueries({
    //NamedQuery(name = "Patientphone.findAll", query = "SELECT p FROM Patientphone p")})
public class Patientphone implements Serializable {

    private static final long serialVersionUID = 1L;
    // //Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    //Id
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 13)
    //Column(name = "Phone")
    private String phone;
    //JoinColumn(name = "Patient_ssn", referencedColumnName = "Patient_ssn")
    //ManyToOne(optional = false)
    private Patient patient;

    public Patientphone() {
    }

    public Patientphone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (phone != null ? phone.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patientphone)) {
            return false;
        }
        Patientphone other = (Patientphone) object;
        if ((this.phone == null && other.phone != null) || (this.phone != null && !this.phone.equals(other.phone))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Patientphone[ phone=" + phone + " ]";
    }
    
}
