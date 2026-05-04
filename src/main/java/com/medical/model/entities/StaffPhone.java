/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.entities;

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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Staff_Phone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaffPhone.findAll", query = "SELECT s FROM StaffPhone s"),
    @NamedQuery(name = "StaffPhone.findByStPhone", query = "SELECT s FROM StaffPhone s WHERE s.stPhone = :stPhone")})
public class StaffPhone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "St_Phone")
    private String stPhone;
    @JoinColumn(name = "Staff_SSN", referencedColumnName = "Staff_SSN")
    @ManyToOne(optional = false)
    private Staff staffSSN;

    public StaffPhone() {
    }

    public StaffPhone(String stPhone) {
        this.stPhone = stPhone;
    }

    public String getStPhone() {
        return stPhone;
    }

    public void setStPhone(String stPhone) {
        this.stPhone = stPhone;
    }

    public Staff getStaffSSN() {
        return staffSSN;
    }

    public void setStaffSSN(Staff staffSSN) {
        this.staffSSN = staffSSN;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stPhone != null ? stPhone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffPhone)) {
            return false;
        }
        StaffPhone other = (StaffPhone) object;
        if ((this.stPhone == null && other.stPhone != null) || (this.stPhone != null && !this.stPhone.equals(other.stPhone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.StaffPhone[ stPhone=" + stPhone + " ]";
    }
    
}
