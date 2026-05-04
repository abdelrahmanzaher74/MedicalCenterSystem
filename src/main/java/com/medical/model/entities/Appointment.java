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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByAppointmentid", query = "SELECT a FROM Appointment a WHERE a.appointmentid = :appointmentid"),
    @NamedQuery(name = "Appointment.findByAppointmentDate", query = "SELECT a FROM Appointment a WHERE a.appointmentDate = :appointmentDate")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Appointment_id")
    private Integer appointmentid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Appointment_Date")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @JoinColumn(name = "Service_id", referencedColumnName = "Service_id")
    @ManyToOne(optional = true)
    private Service serviceid;
    @JoinColumn(name = "Doctor_SSN", referencedColumnName = "Doctor_SSN")
    @ManyToOne(optional = false)
    private Doctors doctorSSN;
    @JoinColumn(name = "Patient_SSN", referencedColumnName = "Patient_ssn")
    @ManyToOne(optional = false)
    private Patient patientSSN;
    @JoinColumn(name = "Method_id", referencedColumnName = "Method_id")
    @ManyToOne(optional = false)
    private PaymentMethod methodid;

    public Appointment() {
    }

    public Appointment(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public Appointment(Integer appointmentid, Date appointmentDate) {
        this.appointmentid = appointmentid;
        this.appointmentDate = appointmentDate;
    }

    public Integer getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Service getServiceid() {
        return serviceid;
    }

    public void setServiceid(Service serviceid) {
        this.serviceid = serviceid;
    }

    public Doctors getDoctorSSN() {
        return doctorSSN;
    }

    public void setDoctorSSN(Doctors doctorSSN) {
        this.doctorSSN = doctorSSN;
    }

    public Patient getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(Patient patientSSN) {
        this.patientSSN = patientSSN;
    }

    public PaymentMethod getMethodid() {
        return methodid;
    }

    public void setMethodid(PaymentMethod methodid) {
        this.methodid = methodid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentid != null ? appointmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointmentid == null && other.appointmentid != null) || (this.appointmentid != null && !this.appointmentid.equals(other.appointmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.medical.model.entities.Appointment[ appointmentid=" + appointmentid + " ]";
    }
    
}
