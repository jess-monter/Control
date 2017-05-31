/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jess
 */
@Entity
@Table(name = "Dirige")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dirige.findAll", query = "SELECT d FROM Dirige d")
    , @NamedQuery(name = "Dirige.findByIdNum", query = "SELECT d FROM Dirige d WHERE d.idNum = :idNum")
    , @NamedQuery(name = "Dirige.findByFechaInicio", query = "SELECT d FROM Dirige d WHERE d.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Dirige.findByRFCEmpresa", query = "SELECT d FROM Dirige d WHERE d.dirigePK.rFCEmpresa = :rFCEmpresa")
    , @NamedQuery(name = "Dirige.findByCurp", query = "SELECT d FROM Dirige d WHERE d.dirigePK.curp = :curp")})
public class Dirige implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DirigePK dirigePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_num")
    private int idNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @JoinColumn(name = "CURP", referencedColumnName = "CURP", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "RFCEmpresa", referencedColumnName = "RFC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Dirige() {
    }

    public Dirige(DirigePK dirigePK) {
        this.dirigePK = dirigePK;
    }

    public Dirige(DirigePK dirigePK, int idNum, Date fechaInicio) {
        this.dirigePK = dirigePK;
        this.idNum = idNum;
        this.fechaInicio = fechaInicio;
    }

    public Dirige(String rFCEmpresa, String curp) {
        this.dirigePK = new DirigePK(rFCEmpresa, curp);
    }

    public DirigePK getDirigePK() {
        return dirigePK;
    }

    public void setDirigePK(DirigePK dirigePK) {
        this.dirigePK = dirigePK;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dirigePK != null ? dirigePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dirige)) {
            return false;
        }
        Dirige other = (Dirige) object;
        if ((this.dirigePK == null && other.dirigePK != null) || (this.dirigePK != null && !this.dirigePK.equals(other.dirigePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dirige[ dirigePK=" + dirigePK + " ]";
    }
    
}
