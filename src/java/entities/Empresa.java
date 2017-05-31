/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jess
 */
@Entity
@Table(name = "Empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByIdNum", query = "SELECT e FROM Empresa e WHERE e.idNum = :idNum")
    , @NamedQuery(name = "Empresa.findByRfc", query = "SELECT e FROM Empresa e WHERE e.rfc = :rfc")
    , @NamedQuery(name = "Empresa.findByRazonSocial", query = "SELECT e FROM Empresa e WHERE e.razonSocial = :razonSocial")
    , @NamedQuery(name = "Empresa.findByCalle", query = "SELECT e FROM Empresa e WHERE e.calle = :calle")
    , @NamedQuery(name = "Empresa.findByNum", query = "SELECT e FROM Empresa e WHERE e.num = :num")
    , @NamedQuery(name = "Empresa.findByCiudad", query = "SELECT e FROM Empresa e WHERE e.ciudad = :ciudad")
    , @NamedQuery(name = "Empresa.findByCp", query = "SELECT e FROM Empresa e WHERE e.cp = :cp")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_num")
    private int idNum;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "razon_social")
    private String razonSocial;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Collection<Trabaja> trabajaCollection;
    @OneToMany(mappedBy = "rFCEmpresa")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Collection<Dirige> dirigeCollection;

    public Empresa() {
    }

    public Empresa(String rfc) {
        this.rfc = rfc;
    }

    public Empresa(String rfc, int idNum, String razonSocial) {
        this.rfc = rfc;
        this.idNum = idNum;
        this.razonSocial = razonSocial;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    @XmlTransient
    public Collection<Trabaja> getTrabajaCollection() {
        return trabajaCollection;
    }

    public void setTrabajaCollection(Collection<Trabaja> trabajaCollection) {
        this.trabajaCollection = trabajaCollection;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @XmlTransient
    public Collection<Dirige> getDirigeCollection() {
        return dirigeCollection;
    }

    public void setDirigeCollection(Collection<Dirige> dirigeCollection) {
        this.dirigeCollection = dirigeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfc != null ? rfc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.rfc == null && other.rfc != null) || (this.rfc != null && !this.rfc.equals(other.rfc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Empresa[ rfc=" + rfc + " ]";
    }
    
}
