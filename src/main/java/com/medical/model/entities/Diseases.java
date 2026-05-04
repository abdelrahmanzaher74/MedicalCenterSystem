/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "Diseases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diseases.findAll", query = "SELECT d FROM Diseases d"),
    @NamedQuery(name = "Diseases.findByDiseaseID", query = "SELECT d FROM Diseases d WHERE d.diseaseID = :diseaseID"),
    @NamedQuery(name = "Diseases.findByDiseaseName", query = "SELECT d FROM Diseases d WHERE d.diseaseName = :diseaseName")})
public class Diseases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Disease_ID")
    private Integer diseaseID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Disease_Name")
    private String diseaseName;
    @JoinTable(name = "Patient_Diseases", joinColumns = {
        @JoinColumn(name = "Disease_ID", referencedColumnName = "Disease_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Patient_SSN", referencedColumnName = "Patient_ssn")})
    @ManyToMany
    private Collection<Patient> patientCollection;

    public Diseases() {
    }

    public Diseases(Integer diseaseID) {
        this.diseaseID = diseaseID;
    }

    public Diseases(Integer diseaseID, String diseaseName) {
        this.diseaseID = diseaseID;
        this.diseaseName = diseaseName;
    }

    public Integer getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(Integer diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @XmlTransient
    public Collection<Patient> getPatientCollection() {
        return patientCollection;
    }

    public void setPatientCollection(Collection<Patient> patientCollection) {
        this.patientCollection = patientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diseaseID != null ? diseaseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diseases)) {
            return false;
        }
        Diseases other = (Diseases) object;
        if ((this.diseaseID == null && other.diseaseID != null) || (this.diseaseID != null && !this.diseaseID.equals(other.diseaseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Diseases[ diseaseID=" + diseaseID + " ]";
    }
    
}
