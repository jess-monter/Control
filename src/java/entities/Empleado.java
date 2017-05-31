/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "Empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdNum", query = "SELECT e FROM Empleado e WHERE e.idNum = :idNum")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByPaterno", query = "SELECT e FROM Empleado e WHERE e.paterno = :paterno")
    , @NamedQuery(name = "Empleado.findByMaterno", query = "SELECT e FROM Empleado e WHERE e.materno = :materno")
    , @NamedQuery(name = "Empleado.findByFNac", query = "SELECT e FROM Empleado e WHERE e.fNac = :fNac")
    , @NamedQuery(name = "Empleado.findByCalle", query = "SELECT e FROM Empleado e WHERE e.calle = :calle")
    , @NamedQuery(name = "Empleado.findByNum", query = "SELECT e FROM Empleado e WHERE e.num = :num")
    , @NamedQuery(name = "Empleado.findByCiudad", query = "SELECT e FROM Empleado e WHERE e.ciudad = :ciudad")
    , @NamedQuery(name = "Empleado.findByCp", query = "SELECT e FROM Empleado e WHERE e.cp = :cp")
    , @NamedQuery(name = "Empleado.findByCurp", query = "SELECT e FROM Empleado e WHERE e.curp = :curp")
    , @NamedQuery(name = "Empleado.findBySupervisorCURP", query = "SELECT e FROM Empleado e WHERE e.supervisorCURP = :supervisorCURP")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_num")
    private int idNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Paterno")
    private String paterno;
    @Size(max = 255)
    @Column(name = "Materno")
    private String materno;
    @Column(name = "f_nac")
    @Temporal(TemporalType.DATE)
    private Date fNac;
    @Size(max = 100)
    @Column(name = "Calle")
    private String calle;
    @Size(max = 15)
    @Column(name = "Num")
    private String num;
    @Size(max = 100)
    @Column(name = "Ciudad")
    private String ciudad;
    @Column(name = "CP")
    private BigInteger cp;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "CURP")
    private String curp;
    @Size(max = 18)
    @Column(name = "SupervisorCURP")
    private String supervisorCURP;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Trabaja> trabajaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Dirige> dirigeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Colabora> colaboraCollection;

    public Empleado() {
    }

    public Empleado(String curp) {
        this.curp = curp;
    }

    public Empleado(String curp, int idNum, String nombre, String paterno) {
        this.curp = curp;
        this.idNum = idNum;
        this.nombre = nombre;
        this.paterno = paterno;
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Date getFNac() {
        return fNac;
    }

    public void setFNac(Date fNac) {
        this.fNac = fNac;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public BigInteger getCp() {
        return cp;
    }

    public void setCp(BigInteger cp) {
        this.cp = cp;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getSupervisorCURP() {
        return supervisorCURP;
    }

    public void setSupervisorCURP(String supervisorCURP) {
        this.supervisorCURP = supervisorCURP;
    }

    @XmlTransient
    public Collection<Trabaja> getTrabajaCollection() {
        return trabajaCollection;
    }

    public void setTrabajaCollection(Collection<Trabaja> trabajaCollection) {
        this.trabajaCollection = trabajaCollection;
    }

    @XmlTransient
    public Collection<Dirige> getDirigeCollection() {
        return dirigeCollection;
    }

    public void setDirigeCollection(Collection<Dirige> dirigeCollection) {
        this.dirigeCollection = dirigeCollection;
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
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Empleado[ curp=" + curp + " ]";
    }
    
}
