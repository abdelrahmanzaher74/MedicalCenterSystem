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
//Table(name = "Blood_type")
//NamedQueries({
    //NamedQuery(name = "Bloodtype.findAll", query = "SELECT b FROM Bloodtype b")})
public class Bloodtype implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    //Basic(optional = false)
    //NotNull
    //Column(name = "Blood_Id")
    private Integer bloodId;
    //Size(max = 50)
    //Column(name = "Blood_name")
    private String bloodname;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "bloodtype")
    private Collection<Transfusion> transfusionCollection;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "bloodtype1")
    private Collection<Transfusion> transfusionCollection1;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "bloodtype")
    private Collection<Patient> patientCollection;

    public Bloodtype() {
    }

    public Bloodtype(Integer bloodId) {
        this.bloodId = bloodId;
    }

    public Integer getBloodId() {
        return bloodId;
    }

    public void setBloodId(Integer bloodId) {
        this.bloodId = bloodId;
    }

    public String getBloodname() {
        return bloodname;
    }

    public void setBloodname(String bloodname) {
        this.bloodname = bloodname;
    }

    public Collection<Transfusion> getTransfusionCollection() {
        return transfusionCollection;
    }

    public void setTransfusionCollection(Collection<Transfusion> transfusionCollection) {
        this.transfusionCollection = transfusionCollection;
    }

    public Collection<Transfusion> getTransfusionCollection1() {
        return transfusionCollection1;
    }

    public void setTransfusionCollection1(Collection<Transfusion> transfusionCollection1) {
        this.transfusionCollection1 = transfusionCollection1;
    }

    public Collection<Patient> getPatientCollection() {
        return patientCollection;
    }

    public void setPatientCollection(Collection<Patient> patientCollection) {
        this.patientCollection = patientCollection;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (bloodId != null ? bloodId.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloodtype)) {
            return false;
        }
        Bloodtype other = (Bloodtype) object;
        if ((this.bloodId == null && other.bloodId != null) || (this.bloodId != null && !this.bloodId.equals(other.bloodId))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Bloodtype[ bloodId=" + bloodId + " ]";
    }
    
}
