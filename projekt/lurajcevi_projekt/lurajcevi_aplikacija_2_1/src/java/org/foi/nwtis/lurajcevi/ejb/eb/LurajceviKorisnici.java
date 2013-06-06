
package org.foi.nwtis.lurajcevi.ejb.eb;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * @document LurajceviKorisnici
 * @author Luka Rajcevic
 */
@Entity
@Table(name = "LURAJCEVI_KORISNICI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LurajceviKorisnici.findAll", query = "SELECT l FROM LurajceviKorisnici l"),
    @NamedQuery(name = "LurajceviKorisnici.findByIme", query = "SELECT l FROM LurajceviKorisnici l WHERE l.ime = :ime"),
    @NamedQuery(name = "LurajceviKorisnici.findByLozinka", query = "SELECT l FROM LurajceviKorisnici l WHERE l.lozinka = :lozinka"),
    @NamedQuery(name = "LurajceviKorisnici.findByTip", query = "SELECT l FROM LurajceviKorisnici l WHERE l.tip = :tip")})
public class LurajceviKorisnici implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IME")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LOZINKA")
    private String lozinka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP")
    private int tip;
    @OneToMany(mappedBy = "vlasnik")
    private List<LurajceviPortfolio> lurajceviPortfolioList;

    public LurajceviKorisnici() {
    }

    public LurajceviKorisnici(String ime) {
        this.ime = ime;
    }

    public LurajceviKorisnici(String ime, String lozinka, int tip) {
        this.ime = ime;
        this.lozinka = lozinka;
        this.tip = tip;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    @XmlTransient
    public List<LurajceviPortfolio> getLurajceviPortfolioList() {
        return lurajceviPortfolioList;
    }

    public void setLurajceviPortfolioList(List<LurajceviPortfolio> lurajceviPortfolioList) {
        this.lurajceviPortfolioList = lurajceviPortfolioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ime != null ? ime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LurajceviKorisnici)) {
            return false;
        }
        LurajceviKorisnici other = (LurajceviKorisnici) object;
        if ((this.ime == null && other.ime != null) || (this.ime != null && !this.ime.equals(other.ime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.foi.nwtis.lurajcevi.ejb.eb.LurajceviKorisnici[ ime=" + ime + " ]";
    }

}
