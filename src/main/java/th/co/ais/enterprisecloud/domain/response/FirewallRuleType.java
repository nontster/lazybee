//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.29 at 01:39:40 PM ICT 
//


package th.co.ais.enterprisecloud.domain.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for firewallRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="firewallRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourceIp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourcePort" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="destinationIp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="destinationPortRange" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="protocol" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="TCP"/>
 *             &lt;enumeration value="UDP"/>
 *             &lt;enumeration value="ICMP"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "firewallRuleType")
public class FirewallRuleType {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "sourceIp", required = true)
    protected String sourceIp;
    @XmlAttribute(name = "sourcePort", required = true)
    protected String sourcePort;
    @XmlAttribute(name = "destinationIp", required = true)
    protected String destinationIp;
    @XmlAttribute(name = "destinationPortRange", required = true)
    protected String destinationPortRange;
    @XmlAttribute(name = "protocol", required = true)
    protected String protocol;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the sourceIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceIp() {
        return sourceIp;
    }

    /**
     * Sets the value of the sourceIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceIp(String value) {
        this.sourceIp = value;
    }

    /**
     * Gets the value of the sourcePort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePort() {
        return sourcePort;
    }

    /**
     * Sets the value of the sourcePort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePort(String value) {
        this.sourcePort = value;
    }

    /**
     * Gets the value of the destinationIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationIp() {
        return destinationIp;
    }

    /**
     * Sets the value of the destinationIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationIp(String value) {
        this.destinationIp = value;
    }

    /**
     * Gets the value of the destinationPortRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationPortRange() {
        return destinationPortRange;
    }

    /**
     * Sets the value of the destinationPortRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationPortRange(String value) {
        this.destinationPortRange = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

}