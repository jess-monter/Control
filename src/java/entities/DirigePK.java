/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jess
 */
@Embeddable
public class DirigePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "RFCEmpresa")
    private String rFCEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "CURP")
    private String curp;

    public DirigePK() {
    }

    public DirigePK(String rFCEmpresa, String curp) {
        this.rFCEmpresa = rFCEmpresa;
        this.curp = curp;
    }

    public String getRFCEmpresa() {
        return rFCEmpresa;
    }

    public void setRFCEmpresa(String rFCEmpresa) {
        this.rFCEmpresa = rFCEmpresa;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rFCEmpresa != null ? rFCEmpresa.hashCode() : 0);
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirigePK)) {
            return false;
        }
        DirigePK other = (DirigePK) object;
        if ((this.rFCEmpresa == null && other.rFCEmpresa != null) || (this.rFCEmpresa != null && !this.rFCEmpresa.equals(other.rFCEmpresa))) {
            return false;
        }
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DirigePK[ rFCEmpresa=" + rFCEmpresa + ", curp=" + curp + " ]";
    }
    
}
