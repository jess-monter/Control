/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Trabaja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabaja.findAll", query = "SELECT t FROM Trabaja t")
    , @NamedQuery(name = "Trabaja.findByIdNum", query = "SELECT t FROM Trabaja t WHERE t.idNum = :idNum")
    , @NamedQuery(name = "Trabaja.findByFechaIngreso", query = "SELECT t FROM Trabaja t WHERE t.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Trabaja.findBySalario", query = "SELECT t FROM Trabaja t WHERE t.salario = :salario")
    , @NamedQuery(name = "Trabaja.findByRFCEmpresa", query = "SELECT t FROM Trabaja t WHERE t.trabajaPK.rFCEmpresa = :rFCEmpresa")
    , @NamedQuery(name = "Trabaja.findByCurp", query = "SELECT t FROM Trabaja t WHERE t.trabajaPK.curp = :curp")})
public class Trabaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrabajaPK trabajaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_num")
    private int idNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salario")
    private BigDecimal salario;
    @JoinColumn(name = "CURP", referencedColumnName = "CURP", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "RFCEmpresa", referencedColumnName = "RFC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Trabaja() {
    }

    public Trabaja(TrabajaPK trabajaPK) {
        this.trabajaPK = trabajaPK;
    }

    public Trabaja(TrabajaPK trabajaPK, int idNum, Date fechaIngreso, BigDecimal salario) {
        this.trabajaPK = trabajaPK;
        this.idNum = idNum;
        this.fechaIngreso = fechaIngreso;
        this.salario = salario;
    }

    public Trabaja(String rFCEmpresa, String curp) {
        this.trabajaPK = new TrabajaPK(rFCEmpresa, curp);
    }

    public TrabajaPK getTrabajaPK() {
        return trabajaPK;
    }

    public void setTrabajaPK(TrabajaPK trabajaPK) {
        this.trabajaPK = trabajaPK;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
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
        hash += (trabajaPK != null ? trabajaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabaja)) {
            return false;
        }
        Trabaja other = (Trabaja) object;
        if ((this.trabajaPK == null && other.trabajaPK != null) || (this.trabajaPK != null && !this.trabajaPK.equals(other.trabajaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trabaja[ trabajaPK=" + trabajaPK + " ]";
    }
    
}
