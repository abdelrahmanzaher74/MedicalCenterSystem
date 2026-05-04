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
@Table(name = "Doctors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctors.findAll", query = "SELECT d FROM Doctors d"),
    @NamedQuery(name = "Doctors.findByDoctorSSN", query = "SELECT d FROM Doctors d WHERE d.doctorSSN = :doctorSSN"),
    @NamedQuery(name = "Doctors.findByDoctorsSpecialization", query = "SELECT d FROM Doctors d WHERE d.doctorsSpecialization = :doctorsSpecialization"),
    @NamedQuery(name = "Doctors.findByDoctorsName", query = "SELECT d FROM Doctors d WHERE d.doctorsName = :doctorsName"),
    @NamedQuery(name = "Doctors.findByDoctorsGender", query = "SELECT d FROM Doctors d WHERE d.doctorsGender = :doctorsGender"),
    @NamedQuery(name = "Doctors.findByDoctorsEmail", query = "SELECT d FROM Doctors d WHERE d.doctorsEmail = :doctorsEmail")})
public class Doctors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Doctor_SSN")
    private String doctorSSN;
    @Size(max = 50)
    @Column(name = "Doctors_Specialization")
    private String doctorsSpecialization;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Doctors_Name")
    private String doctorsName;
    @Column(name = "Doctors_Gender")
    private Character doctorsGender;
    @Size(max = 150)
    @Column(name = "Doctors_Email")
    private String doctorsEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorSSN")
    private Collection<Appointment> appointmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorSSN")
    private Collection<Doctorphone> doctorphoneCollection;
    @JoinColumn(name = "Department_id", referencedColumnName = "Department_id")
    @ManyToOne(optional = false)
    private Departments departmentid;
    @JoinColumn(name = "Price_id", referencedColumnName = "Price_id")
    @ManyToOne(optional = false)
    private Prices priceid;

    public Doctors() {
    }

    public Doctors(String doctorSSN) {
        this.doctorSSN = doctorSSN;
    }

    public Doctors(String doctorSSN, String doctorsName) {
        this.doctorSSN = doctorSSN;
        this.doctorsName = doctorsName;
    }

    public String getDoctorSSN() {
        return doctorSSN;
    }

    public void setDoctorSSN(String doctorSSN) {
        this.doctorSSN = doctorSSN;
    }

    public String getDoctorsSpecialization() {
        return doctorsSpecialization;
    }

    public void setDoctorsSpecialization(String doctorsSpecialization) {
        this.doctorsSpecialization = doctorsSpecialization;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public Character getDoctorsGender() {
        return doctorsGender;
    }

    public void setDoctorsGender(Character doctorsGender) {
        this.doctorsGender = doctorsGender;
    }

    public String getDoctorsEmail() {
        return doctorsEmail;
    }

    public void setDoctorsEmail(String doctorsEmail) {
        this.doctorsEmail = doctorsEmail;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    @XmlTransient
    public Collection<Doctorphone> getDoctorphoneCollection() {
        return doctorphoneCollection;
    }

    public void setDoctorphoneCollection(Collection<Doctorphone> doctorphoneCollection) {
        this.doctorphoneCollection = doctorphoneCollection;
    }

    public Departments getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Departments departmentid) {
        this.departmentid = departmentid;
    }

    public Prices getPriceid() {
        return priceid;
    }

    public void setPriceid(Prices priceid) {
        this.priceid = priceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doctorSSN != null ? doctorSSN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctors)) {
            return false;
        }
        Doctors other = (Doctors) object;
        if ((this.doctorSSN == null && other.doctorSSN != null) || (this.doctorSSN != null && !this.doctorSSN.equals(other.doctorSSN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Doctors[ doctorSSN=" + doctorSSN + " ]";
    }
    
}
