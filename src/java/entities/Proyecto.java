/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jess
 */
@Entity
@Table(name = "Proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByIdNum", query = "SELECT p FROM Proyecto p WHERE p.idNum = :idNum")
    , @NamedQuery(name = "Proyecto.findByNombre", query = "SELECT p FROM Proyecto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Proyecto.findByNumProy", query = "SELECT p FROM Proyecto p WHERE p.numProy = :numProy")
    , @NamedQuery(name = "Proyecto.findByFechaInicio", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Proyecto.findByFechaFin", query = "SELECT p FROM Proyecto p WHERE p.fechaFin = :fechaFin")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_num")
    private int idNum;
    @Size(max = 200)
    @Column(name = "Nombre")
    private String nombre;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "numProy")
    private String numProy;
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
    @JoinColumn(name = "RFCEmpresa", referencedColumnName = "RFC")
    @ManyToOne
    private Empresa rFCEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<Colabora> colaboraCollection;

    public Proyecto() {
    }

    public Proyecto(String numProy) {
        this.numProy = numProy;
    }

    public Proyecto(String numProy, int idNum, Date fechaInicio, Date fechaFin) {
        this.numProy = numProy;
        this.idNum = idNum;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumProy() {
        return numProy;
    }

    public void setNumProy(String numProy) {
        this.numProy = numProy;
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

    public Empresa getRFCEmpresa() {
        return rFCEmpresa;
    }

    public void setRFCEmpresa(Empresa rFCEmpresa) {
        this.rFCEmpresa = rFCEmpresa;
    }

    @XmlTransient
    public Collection<Colabora> getColaboraCollection() {
        return colaboraCollection;
    }

    public void setColaboraCollection(Collection<Colabora> colaboraCollection) {
        this.colaboraCollection = colaboraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numProy != null ? numProy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.numProy == null && other.numProy != null) || (this.numProy != null && !this.numProy.equals(other.numProy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Proyecto[ numProy=" + numProy + " ]";
    }
    
}
