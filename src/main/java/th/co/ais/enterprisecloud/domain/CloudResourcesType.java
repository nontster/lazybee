//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 01:44:53 PM ICT 
//


package th.co.ais.enterprisecloud.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CloudResourcesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CloudResourcesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="providerVdc" type="{http://enterprisecloud.ais.co.th/bot}ProviderVdcType" minOccurs="0"/>
 *         &lt;element name="networkPool" type="{http://enterprisecloud.ais.co.th/bot}NetworkPoolType" minOccurs="0"/>
 *         &lt;element name="externalNetwork" type="{http://enterprisecloud.ais.co.th/bot}ExternalNetworkType" minOccurs="0"/>
 *         &lt;element name="catalog" type="{http://enterprisecloud.ais.co.th/bot}CatalogType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CloudResourcesType", propOrder = {

})
public class CloudResourcesType {

    protected ProviderVdcType providerVdc;
    protected NetworkPoolType networkPool;
    protected ExternalNetworkType externalNetwork;
    protected CatalogType catalog;

    /**
     * Gets the value of the providerVdc property.
     * 
     * @return
     *     possible object is
     *     {@link ProviderVdcType }
     *     
     */
    public ProviderVdcType getProviderVdc() {
        return providerVdc;
    }

    /**
     * Sets the value of the providerVdc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderVdcType }
     *     
     */
    public void setProviderVdc(ProviderVdcType value) {
        this.providerVdc = value;
    }

    /**
     * Gets the value of the networkPool property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkPoolType }
     *     
     */
    public NetworkPoolType getNetworkPool() {
        return networkPool;
    }

    /**
     * Sets the value of the networkPool property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkPoolType }
     *     
     */
    public void setNetworkPool(NetworkPoolType value) {
        this.networkPool = value;
    }

    /**
     * Gets the value of the externalNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link ExternalNetworkType }
     *     
     */
    public ExternalNetworkType getExternalNetwork() {
        return externalNetwork;
    }

    /**
     * Sets the value of the externalNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalNetworkType }
     *     
     */
    public void setExternalNetwork(ExternalNetworkType value) {
        this.externalNetwork = value;
    }

    /**
     * Gets the value of the catalog property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogType }
     *     
     */
    public CatalogType getCatalog() {
        return catalog;
    }

    /**
     * Sets the value of the catalog property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogType }
     *     
     */
    public void setCatalog(CatalogType value) {
        this.catalog = value;
    }

}