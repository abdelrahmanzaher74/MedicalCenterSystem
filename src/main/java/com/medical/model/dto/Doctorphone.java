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
//Table(name = "Doctor_phone")
//NamedQueries({
    //NamedQuery(name = "Doctorphone.findAll", query = "SELECT d FROM Doctorphone d")})
public class Doctorphone implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 13)
    //Column(name = "Doc_Phone")
    private String docPhone;
    //JoinColumn(name = "Doctor_SSN", referencedColumnName = "Doctor_SSN")
    //ManyToOne(optional = false)
    private Doctors doctors;

    public Doctorphone() {
    }

    public Doctorphone(String docPhone) {
        this.docPhone = docPhone;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (docPhone != null ? docPhone.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctorphone)) {
            return false;
        }
        Doctorphone other = (Doctorphone) object;
        if ((this.docPhone == null && other.docPhone != null) || (this.docPhone != null && !this.docPhone.equals(other.docPhone))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Doctorphone[ docPhone=" + docPhone + " ]";
    }
    
}
