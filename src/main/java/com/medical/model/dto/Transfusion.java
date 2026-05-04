/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * //author dell
 */
//Entity
//Table(name = "Transfusion")
//NamedQueries({
    //NamedQuery(name = "Transfusion.findAll", query = "SELECT t FROM Transfusion t")})
public class Transfusion implements Serializable {

    private static final long serialVersionUID = 1L;
    //EmbeddedId
    protected TransfusionPK transfusionPK;
    //JoinColumn(name = "Donor_Id", referencedColumnName = "Blood_Id", insertable = false, updatable = false)
    //ManyToOne(optional = false)
    private Bloodtype bloodtype;
    //JoinColumn(name = "Receiver_Id", referencedColumnName = "Blood_Id", insertable = false, updatable = false)
    //ManyToOne(optional = false)
    private Bloodtype bloodtype1;
    //OneToOne(cascade = CascadeType.ALL, mappedBy = "transfusion1")
    private Transfusion transfusion;
    //JoinColumns({
        //JoinColumn(name = "Receiver_Id", referencedColumnName = "Donor_Id", insertable = false, updatable = false),
        //JoinColumn(name = "Donor_Id", referencedColumnName = "Receiver_Id", insertable = false, updatable = false)})
    //OneToOne(optional = false)
    private Transfusion transfusion1;

    public Transfusion() {
    }

    public Transfusion(TransfusionPK transfusionPK) {
        this.transfusionPK = transfusionPK;
    }

    public Transfusion(int donorId, int receiverId) {
        this.transfusionPK = new TransfusionPK(donorId, receiverId);
    }

    public TransfusionPK getTransfusionPK() {
        return transfusionPK;
    }

    public void setTransfusionPK(TransfusionPK transfusionPK) {
        this.transfusionPK = transfusionPK;
    }

    public Bloodtype getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(Bloodtype bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Bloodtype getBloodtype1() {
        return bloodtype1;
    }

    public void setBloodtype1(Bloodtype bloodtype1) {
        this.bloodtype1 = bloodtype1;
    }

    public Transfusion getTransfusion() {
        return transfusion;
    }

    public void setTransfusion(Transfusion transfusion) {
        this.transfusion = transfusion;
    }

    public Transfusion getTransfusion1() {
        return transfusion1;
    }

    public void setTransfusion1(Transfusion transfusion1) {
        this.transfusion1 = transfusion1;
    }

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (transfusionPK != null ? transfusionPK.hashCode() : 0);
        return hash;
    }

    //Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfusion)) {
            return false;
        }
        Transfusion other = (Transfusion) object;
        if ((this.transfusionPK == null && other.transfusionPK != null) || (this.transfusionPK != null && !this.transfusionPK.equals(other.transfusionPK))) {
            return false;
        }
        return true;
    }

    //Override
    public String toString() {
        return "com.medical.model.dto.Transfusion[ transfusionPK=" + transfusionPK + " ]";
    }
    
}
