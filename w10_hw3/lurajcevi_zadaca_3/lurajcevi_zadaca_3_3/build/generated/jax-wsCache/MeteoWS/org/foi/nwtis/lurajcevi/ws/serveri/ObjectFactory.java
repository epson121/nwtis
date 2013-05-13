
package org.foi.nwtis.lurajcevi.ws.serveri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.foi.nwtis.lurajcevi.ws.serveri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DajMeteoWSPodatkeZaViseZipResponse_QNAME = new QName("http://serveri.ws.lurajcevi.nwtis.foi.org/", "dajMeteoWSPodatkeZaViseZipResponse");
    private final static QName _DajMeteoWSPodatkeZaZip_QNAME = new QName("http://serveri.ws.lurajcevi.nwtis.foi.org/", "dajMeteoWSPodatkeZaZip");
    private final static QName _DajMeteoWSPodatkeZaZipResponse_QNAME = new QName("http://serveri.ws.lurajcevi.nwtis.foi.org/", "dajMeteoWSPodatkeZaZipResponse");
    private final static QName _DajMeteoWSPodatkeZaViseZip_QNAME = new QName("http://serveri.ws.lurajcevi.nwtis.foi.org/", "dajMeteoWSPodatkeZaViseZip");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.foi.nwtis.lurajcevi.ws.serveri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DajMeteoWSPodatkeZaViseZip }
     * 
     */
    public DajMeteoWSPodatkeZaViseZip createDajMeteoWSPodatkeZaViseZip() {
        return new DajMeteoWSPodatkeZaViseZip();
    }

    /**
     * Create an instance of {@link DajMeteoWSPodatkeZaZipResponse }
     * 
     */
    public DajMeteoWSPodatkeZaZipResponse createDajMeteoWSPodatkeZaZipResponse() {
        return new DajMeteoWSPodatkeZaZipResponse();
    }

    /**
     * Create an instance of {@link DajMeteoWSPodatkeZaZip }
     * 
     */
    public DajMeteoWSPodatkeZaZip createDajMeteoWSPodatkeZaZip() {
        return new DajMeteoWSPodatkeZaZip();
    }

    /**
     * Create an instance of {@link DajMeteoWSPodatkeZaViseZipResponse }
     * 
     */
    public DajMeteoWSPodatkeZaViseZipResponse createDajMeteoWSPodatkeZaViseZipResponse() {
        return new DajMeteoWSPodatkeZaViseZipResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajMeteoWSPodatkeZaViseZipResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serveri.ws.lurajcevi.nwtis.foi.org/", name = "dajMeteoWSPodatkeZaViseZipResponse")
    public JAXBElement<DajMeteoWSPodatkeZaViseZipResponse> createDajMeteoWSPodatkeZaViseZipResponse(DajMeteoWSPodatkeZaViseZipResponse value) {
        return new JAXBElement<DajMeteoWSPodatkeZaViseZipResponse>(_DajMeteoWSPodatkeZaViseZipResponse_QNAME, DajMeteoWSPodatkeZaViseZipResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajMeteoWSPodatkeZaZip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serveri.ws.lurajcevi.nwtis.foi.org/", name = "dajMeteoWSPodatkeZaZip")
    public JAXBElement<DajMeteoWSPodatkeZaZip> createDajMeteoWSPodatkeZaZip(DajMeteoWSPodatkeZaZip value) {
        return new JAXBElement<DajMeteoWSPodatkeZaZip>(_DajMeteoWSPodatkeZaZip_QNAME, DajMeteoWSPodatkeZaZip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajMeteoWSPodatkeZaZipResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serveri.ws.lurajcevi.nwtis.foi.org/", name = "dajMeteoWSPodatkeZaZipResponse")
    public JAXBElement<DajMeteoWSPodatkeZaZipResponse> createDajMeteoWSPodatkeZaZipResponse(DajMeteoWSPodatkeZaZipResponse value) {
        return new JAXBElement<DajMeteoWSPodatkeZaZipResponse>(_DajMeteoWSPodatkeZaZipResponse_QNAME, DajMeteoWSPodatkeZaZipResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajMeteoWSPodatkeZaViseZip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serveri.ws.lurajcevi.nwtis.foi.org/", name = "dajMeteoWSPodatkeZaViseZip")
    public JAXBElement<DajMeteoWSPodatkeZaViseZip> createDajMeteoWSPodatkeZaViseZip(DajMeteoWSPodatkeZaViseZip value) {
        return new JAXBElement<DajMeteoWSPodatkeZaViseZip>(_DajMeteoWSPodatkeZaViseZip_QNAME, DajMeteoWSPodatkeZaViseZip.class, null, value);
    }

}
