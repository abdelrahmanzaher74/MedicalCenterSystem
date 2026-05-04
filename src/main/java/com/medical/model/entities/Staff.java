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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findByStaffSSN", query = "SELECT s FROM Staff s WHERE s.staffSSN = :staffSSN"),
    @NamedQuery(name = "Staff.findByStName", query = "SELECT s FROM Staff s WHERE s.stName = :stName"),
    @NamedQuery(name = "Staff.findByStGender", query = "SELECT s FROM Staff s WHERE s.stGender = :stGender"),
    @NamedQuery(name = "Staff.findByStEmail", query = "SELECT s FROM Staff s WHERE s.stEmail = :stEmail"),
    @NamedQuery(name = "Staff.findByStSalary", query = "SELECT s FROM Staff s WHERE s.stSalary = :stSalary"),
    @NamedQuery(name = "Staff.findByStStartDate", query = "SELECT s FROM Staff s WHERE s.stStartDate = :stStartDate")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Staff_SSN")
    private String staffSSN;
    @Size(max = 30)
    @Column(name = "St_Name")
    private String stName;
    @Column(name = "St_Gender")
    private Character stGender;
    @Size(max = 30)
    @Column(name = "St_Email")
    private String stEmail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "St_Salary")
    private BigDecimal stSalary;
    @Column(name = "St_Start_Date")
    @Temporal(TemporalType.DATE)
    private Date stStartDate;
    @JoinColumn(name = "Department_id", referencedColumnName = "Department_id")
    @ManyToOne(optional = false)
    private Departments departmentid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffSSN")
    private Collection<StaffPhone> staffPhoneCollection;

    public Staff() {
    }

    public Staff(String staffSSN) {
        this.staffSSN = staffSSN;
    }

    public String getStaffSSN() {
        return staffSSN;
    }

    public void setStaffSSN(String staffSSN) {
        this.staffSSN = staffSSN;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public Character getStGender() {
        return stGender;
    }

    public void setStGender(Character stGender) {
        this.stGender = stGender;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public BigDecimal getStSalary() {
        return stSalary;
    }

    public void setStSalary(BigDecimal stSalary) {
        this.stSalary = stSalary;
    }

    public Date getStStartDate() {
        return stStartDate;
    }

    public void setStStartDate(Date stStartDate) {
        this.stStartDate = stStartDate;
    }

    public Departments getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Departments departmentid) {
        this.departmentid = departmentid;
    }

    @XmlTransient
    public Collection<StaffPhone> getStaffPhoneCollection() {
        return staffPhoneCollection;
    }

    public void setStaffPhoneCollection(Collection<StaffPhone> staffPhoneCollection) {
        this.staffPhoneCollection = staffPhoneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffSSN != null ? staffSSN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.staffSSN == null && other.staffSSN != null) || (this.staffSSN != null && !this.staffSSN.equals(other.staffSSN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Staff[ staffSSN=" + staffSSN + " ]";
    }
    
}
