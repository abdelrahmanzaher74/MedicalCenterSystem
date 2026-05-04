/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author dell
 */
@Embeddable
public class TransfusionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Donor_Id")
    private int donorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Receiver_Id")
    private int receiverId;

    public TransfusionPK() {
    }

    public TransfusionPK(int donorId, int receiverId) {
        this.donorId = donorId;
        this.receiverId = receiverId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) donorId;
        hash += (int) receiverId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransfusionPK)) {
            return false;
        }
        TransfusionPK other = (TransfusionPK) object;
        if (this.donorId != other.donorId) {
            return false;
        }
        if (this.receiverId != other.receiverId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.TransfusionPK[ donorId=" + donorId + ", receiverId=" + receiverId + " ]";
    }
    
}
