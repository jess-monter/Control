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
public class ColaboraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "CURPcolaborador")
    private String cURPcolaborador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "numProyecto")
    private String numProyecto;

    public ColaboraPK() {
    }

    public ColaboraPK(String cURPcolaborador, String numProyecto) {
        this.cURPcolaborador = cURPcolaborador;
        this.numProyecto = numProyecto;
    }

    public String getCURPcolaborador() {
        return cURPcolaborador;
    }

    public void setCURPcolaborador(String cURPcolaborador) {
        this.cURPcolaborador = cURPcolaborador;
    }

    public String getNumProyecto() {
        return numProyecto;
    }

    public void setNumProyecto(String numProyecto) {
        this.numProyecto = numProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cURPcolaborador != null ? cURPcolaborador.hashCode() : 0);
        hash += (numProyecto != null ? numProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColaboraPK)) {
            return false;
        }
        ColaboraPK other = (ColaboraPK) object;
        if ((this.cURPcolaborador == null && other.cURPcolaborador != null) || (this.cURPcolaborador != null && !this.cURPcolaborador.equals(other.cURPcolaborador))) {
            return false;
        }
        if ((this.numProyecto == null && other.numProyecto != null) || (this.numProyecto != null && !this.numProyecto.equals(other.numProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ColaboraPK[ cURPcolaborador=" + cURPcolaborador + ", numProyecto=" + numProyecto + " ]";
    }
    
}
