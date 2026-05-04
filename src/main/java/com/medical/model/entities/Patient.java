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
import jakarta.persistence.ManyToMany;
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
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByPatientssn", query = "SELECT p FROM Patient p WHERE p.patientssn = :patientssn"),
    @NamedQuery(name = "Patient.findByPatientName", query = "SELECT p FROM Patient p WHERE p.patientName = :patientName"),
    @NamedQuery(name = "Patient.findByPatientEmail", query = "SELECT p FROM Patient p WHERE p.patientEmail = :patientEmail"),
    @NamedQuery(name = "Patient.findByPatientGender", query = "SELECT p FROM Patient p WHERE p.patientGender = :patientGender"),
    @NamedQuery(name = "Patient.findByPatientMaritalstatus", query = "SELECT p FROM Patient p WHERE p.patientMaritalstatus = :patientMaritalstatus"),
    @NamedQuery(name = "Patient.findByPatientBirthDate", query = "SELECT p FROM Patient p WHERE p.patientBirthDate = :patientBirthDate"),
    @NamedQuery(name = "Patient.findByPatientAdress", query = "SELECT p FROM Patient p WHERE p.patientAdress = :patientAdress")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Patient_ssn")
    private String patientssn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Patient_Name")
    private String patientName;
    @Size(max = 150)
    @Column(name = "Patient_Email")
    private String patientEmail;
    @Column(name = "Patient_Gender")
    private Character patientGender;
    @Size(max = 20)
    @Column(name = "Patient_Marital_status")
    private String patientMaritalstatus;
    @Column(name = "Patient_Birth_Date")
    @Temporal(TemporalType.DATE)
    private Date patientBirthDate;
    @Size(max = 2147483647)
    @Column(name = "Patient_Adress")
    private String patientAdress;
    @ManyToMany(mappedBy = "patientCollection")
    private Collection<Diseases> diseasesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientSSN")
    private Collection<Appointment> appointmentCollection;
    @JoinColumn(name = "Blood_Id", referencedColumnName = "Blood_Id")
    @ManyToOne(optional = false)
    private Bloodtype bloodId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientssn")
    private Collection<Patientphone> patientphoneCollection;

    public Patient() {
    }

    public Patient(String patientssn) {
        this.patientssn = patientssn;
    }

    public Patient(String patientssn, String patientName) {
        this.patientssn = patientssn;
        this.patientName = patientName;
    }

    public String getPatientssn() {
        return patientssn;
    }

    public void setPatientssn(String patientssn) {
        this.patientssn = patientssn;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Character getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Character patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientMaritalstatus() {
        return patientMaritalstatus;
    }

    public void setPatientMaritalstatus(String patientMaritalstatus) {
        this.patientMaritalstatus = patientMaritalstatus;
    }

    public Date getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public String getPatientAdress() {
        return patientAdress;
    }

    public void setPatientAdress(String patientAdress) {
        this.patientAdress = patientAdress;
    }

    @XmlTransient
    public Collection<Diseases> getDiseasesCollection() {
        return diseasesCollection;
    }

    public void setDiseasesCollection(Collection<Diseases> diseasesCollection) {
        this.diseasesCollection = diseasesCollection;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    public Bloodtype getBloodId() {
        return bloodId;
    }

    public void setBloodId(Bloodtype bloodId) {
        this.bloodId = bloodId;
    }

    @XmlTransient
    public Collection<Patientphone> getPatientphoneCollection() {
        return patientphoneCollection;
    }

    public void setPatientphoneCollection(Collection<Patientphone> patientphoneCollection) {
        this.patientphoneCollection = patientphoneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientssn != null ? patientssn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.patientssn == null && other.patientssn != null) || (this.patientssn != null && !this.patientssn.equals(other.patientssn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Patient[ patientssn=" + patientssn + " ]";
    }
    
}
