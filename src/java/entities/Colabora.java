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
@Table(name = "Colabora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colabora.findAll", query = "SELECT c FROM Colabora c")
    , @NamedQuery(name = "Colabora.findByIdNum", query = "SELECT c FROM Colabora c WHERE c.idNum = :idNum")
    , @NamedQuery(name = "Colabora.findByCURPcolaborador", query = "SELECT c FROM Colabora c WHERE c.colaboraPK.cURPcolaborador = :cURPcolaborador")
    , @NamedQuery(name = "Colabora.findByFechaInicio", query = "SELECT c FROM Colabora c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Colabora.findByFechaFin", query = "SELECT c FROM Colabora c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "Colabora.findByNumHoras", query = "SELECT c FROM Colabora c WHERE c.numHoras = :numHoras")
    , @NamedQuery(name = "Colabora.findByNumProyecto", query = "SELECT c FROM Colabora c WHERE c.colaboraPK.numProyecto = :numProyecto")})
public class Colabora implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ColaboraPK colaboraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_num")
    private int idNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numHoras")
    private int numHoras;
    @JoinColumn(name = "CURPcolaborador", referencedColumnName = "CURP", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "numProyecto", referencedColumnName = "numProy", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public Colabora() {
    }

    public Colabora(ColaboraPK colaboraPK) {
        this.colaboraPK = colaboraPK;
    }

    public Colabora(ColaboraPK colaboraPK, int idNum, Date fechaInicio, Date fechaFin, int numHoras) {
        this.colaboraPK = colaboraPK;
        this.idNum = idNum;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numHoras = numHoras;
    }

    public Colabora(String cURPcolaborador, String numProyecto) {
        this.colaboraPK = new ColaboraPK(cURPcolaborador, numProyecto);
    }

    public ColaboraPK getColaboraPK() {
        return colaboraPK;
    }

    public void setColaboraPK(ColaboraPK colaboraPK) {
        this.colaboraPK = colaboraPK;
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

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colaboraPK != null ? colaboraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colabora)) {
            return false;
        }
        Colabora other = (Colabora) object;
        if ((this.colaboraPK == null && other.colaboraPK != null) || (this.colaboraPK != null && !this.colaboraPK.equals(other.colaboraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Colabora[ colaboraPK=" + colaboraPK + " ]";
    }
    
}
