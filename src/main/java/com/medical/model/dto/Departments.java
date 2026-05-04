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
//Table(name = "Departments")
//NamedQueries({
    //NamedQuery(name = "Departments.findAll", query = "SELECT d FROM Departments d")})
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    //Basic(optional = false)
    //NotNull
    //Column(name = "Department_id")
    private Integer departmentid;
    //Basic(optional = false)
    //NotNull
    //Size(min = 1, max = 50)
    //Column(name = "Dep_Name")
    private String depName;
    //Size(max = 13)
    //Column(name = "Dep_Phone")
    private String depPhone;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "departments")
    private Collection<Staff> staffCollection;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "departments")
    private Collection<Doctors> doctorsCollection;

    public Departments() {
    }

    public Departments(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public Departments(Integer departmentid, String depName) {
        this.departmentid = departmentid;
        this.depName = depName;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepPhone() {
        return depPhone;
    }

    public void setDepPhone(String depPhone) {
        this.depPhone = depPhone;
    }

    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }

    public Collection<Doctors> getDoctorsCollection() {
        return doctorsCollection;
    }

    public void setDoctorsCollection(Collection<Doctors> doctorsCollection) {
        this.doctorsCollection = doctorsCollection;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (departmentid != null ? departmentid.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departments)) {
            return false;
        }
        Departments other = (Departments) object;
        if ((this.departmentid == null && other.departmentid != null) || (this.departmentid != null && !this.departmentid.equals(other.departmentid))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Departments[ departmentid=" + departmentid + " ]";
    }
    
}
