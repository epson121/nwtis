
package org.foi.nwtis.lurajcevi.ejb.eb;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @document LurajceviZP
 * @author Luka Rajcevic
 */
@Entity
@Table(name = "LURAJCEVI_Z_P")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LurajceviZP.findAll", query = "SELECT l FROM LurajceviZP l"),
    @NamedQuery(name = "LurajceviZP.findById", query = "SELECT l FROM LurajceviZP l WHERE l.id = :id")})
public class LurajceviZP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ZIPCODE", referencedColumnName = "ZIP")
    @ManyToOne
    private ZipCodes zipcode;
    @JoinColumn(name = "PORTFOLIO", referencedColumnName = "ID")
    @ManyToOne
    private LurajceviPortfolio portfolio;

    public LurajceviZP() {
    }

    public LurajceviZP(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ZipCodes getZipcode() {
        return zipcode;
    }

    public void setZipcode(ZipCodes zipcode) {
        this.zipcode = zipcode;
    }

    public LurajceviPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(LurajceviPortfolio portfolio) {
        this.portfolio = portfolio;
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
        if (!(object instanceof LurajceviZP)) {
            return false;
        }
        LurajceviZP other = (LurajceviZP) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.foi.nwtis.lurajcevi.ejb.eb.LurajceviZP[ id=" + id + " ]";
    }

}
