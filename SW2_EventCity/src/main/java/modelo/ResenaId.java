
package modelo;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class ResenaId implements Serializable {
    private int idSuscriptor;
    private int idEvento;

    // Getters and Setters
    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idSuscriptor;
        hash = 31 * hash + this.idEvento;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResenaId other = (ResenaId) obj;
        if (this.idSuscriptor != other.idSuscriptor) {
            return false;
        }
        if (this.idEvento != other.idEvento) {
            return false;
        }
        return true;
    }
}