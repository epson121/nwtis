
package org.foi.nwtis.lurajcevi.ejb.eb;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @document LurajceviPortfolio
 * @author Luka Rajcevic
 */
@Entity
@Table(name = "LURAJCEVI_PORTFOLIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LurajceviPortfolio.findAll", query = "SELECT l FROM LurajceviPortfolio l"),
    @NamedQuery(name = "LurajceviPortfolio.findById", query = "SELECT l FROM LurajceviPortfolio l WHERE l.id = :id"),
    @NamedQuery(name = "LurajceviPortfolio.findByNaziv", query = "SELECT l FROM LurajceviPortfolio l WHERE l.naziv = :naziv")})
public class LurajceviPortfolio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAZIV")
    private String naziv;
    @OneToMany(mappedBy = "portfolio")
    private List<LurajceviZP> lurajceviZPList;
    @JoinColumn(name = "VLASNIK", referencedColumnName = "IME")
    @ManyToOne
    private LurajceviKorisnici vlasnik;

    public LurajceviPortfolio() {
    }

    public LurajceviPortfolio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<LurajceviZP> getLurajceviZPList() {
        return lurajceviZPList;
    }

    public void setLurajceviZPList(List<LurajceviZP> lurajceviZPList) {
        this.lurajceviZPList = lurajceviZPList;
    }

    public LurajceviKorisnici getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(LurajceviKorisnici vlasnik) {
        this.vlasnik = vlasnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LurajceviPortfolio)) {
            return false;
        }
        LurajceviPortfolio other = (LurajceviPortfolio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio[ id=" + id + " ]";
    }

}
