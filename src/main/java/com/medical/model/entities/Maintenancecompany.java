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
import java.util.Collection;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Maintenance_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenancecompany.findAll", query = "SELECT m FROM Maintenancecompany m"),
    @NamedQuery(name = "Maintenancecompany.findByCompanyid", query = "SELECT m FROM Maintenancecompany m WHERE m.companyid = :companyid"),
    @NamedQuery(name = "Maintenancecompany.findByCompanyname", query = "SELECT m FROM Maintenancecompany m WHERE m.companyname = :companyname")})
public class Maintenancecompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Company_id")
    private Integer companyid;
    @Size(max = 50)
    @Column(name = "Company_name")
    private String companyname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyid")
    private Collection<Maintenance> maintenanceCollection;

    public Maintenancecompany() {
    }

    public Maintenancecompany(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @XmlTransient
    public Collection<Maintenance> getMaintenanceCollection() {
        return maintenanceCollection;
    }

    public void setMaintenanceCollection(Collection<Maintenance> maintenanceCollection) {
        this.maintenanceCollection = maintenanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyid != null ? companyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maintenancecompany)) {
            return false;
        }
        Maintenancecompany other = (Maintenancecompany) object;
        if ((this.companyid == null && other.companyid != null) || (this.companyid != null && !this.companyid.equals(other.companyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Maintenancecompany[ companyid=" + companyid + " ]";
    }
    
}
