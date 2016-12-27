//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 01:44:53 PM ICT 
//


package th.co.ais.enterprisecloud.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubnetParticipationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubnetParticipationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ipRange" type="{http://enterprisecloud.ais.co.th/bot}IpRangesType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="gateway" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="netmask" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubnetParticipationType", propOrder = {
    "ipRange"
})
public class SubnetParticipationType {

    @XmlElement(required = true)
    protected List<IpRangesType> ipRange;
    @XmlAttribute(name = "gateway")
    protected String gateway;
    @XmlAttribute(name = "netmask")
    protected String netmask;

    /**
     * Gets the value of the ipRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ipRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIpRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IpRangesType }
     * 
     * 
     */
    public List<IpRangesType> getIpRange() {
        if (ipRange == null) {
            ipRange = new ArrayList<IpRangesType>();
        }
        return this.ipRange;
    }

    /**
     * Gets the value of the gateway property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * Sets the value of the gateway property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateway(String value) {
        this.gateway = value;
    }

    /**
     * Gets the value of the netmask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetmask() {
        return netmask;
    }

    /**
     * Sets the value of the netmask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetmask(String value) {
        this.netmask = value;
    }

}