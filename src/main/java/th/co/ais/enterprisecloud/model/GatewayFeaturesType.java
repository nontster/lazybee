//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 01:44:53 PM ICT 
//


package th.co.ais.enterprisecloud.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GatewayFeaturesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GatewayFeaturesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firewallService" type="{http://enterprisecloud.ais.co.th/bot}FirewallServiceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GatewayFeaturesType", propOrder = {
    "firewallService"
})
public class GatewayFeaturesType {

    @XmlElement(required = true)
    protected FirewallServiceType firewallService;

    /**
     * Gets the value of the firewallService property.
     * 
     * @return
     *     possible object is
     *     {@link FirewallServiceType }
     *     
     */
    public FirewallServiceType getFirewallService() {
        return firewallService;
    }

    /**
     * Sets the value of the firewallService property.
     * 
     * @param value
     *     allowed object is
     *     {@link FirewallServiceType }
     *     
     */
    public void setFirewallService(FirewallServiceType value) {
        this.firewallService = value;
    }

}
