
package org.foi.nwtis.lurajcevi.ws.serveri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dajMeteoWSPodatkeZaViseZip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dajMeteoWSPodatkeZaViseZip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zipovi" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajMeteoWSPodatkeZaViseZip", propOrder = {
    "zipovi"
})
public class DajMeteoWSPodatkeZaViseZip {

    protected List<String> zipovi;

    /**
     * Gets the value of the zipovi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zipovi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZipovi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getZipovi() {
        if (zipovi == null) {
            zipovi = new ArrayList<String>();
        }
        return this.zipovi;
    }

}
