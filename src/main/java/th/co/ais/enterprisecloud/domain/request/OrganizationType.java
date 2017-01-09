//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.19 at 10:03:24 AM ICT 
//


package th.co.ais.enterprisecloud.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for organizationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organizationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="users" type="{http://enterprisecloud.ais.co.th/bot}usersType"/>
 *         &lt;element name="vms" type="{http://enterprisecloud.ais.co.th/bot}vmsType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="orderId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="3"/>
 *             &lt;maxLength value="512"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="orderType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="trial"/>
 *             &lt;enumeration value="standard"/>
 *             &lt;enumeration value="customize"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="caNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="shortName" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="3"/>
 *             &lt;maxLength value="64"/>
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
@XmlType(name = "organizationType", propOrder = {
    "users",
    "vms"
})
public class OrganizationType {

    @XmlElement(required = true)
    protected UsersType users;
    @XmlElement(required = true)
    protected VmsType vms;
    @XmlAttribute(name = "orderId", required = true)
    protected String orderId;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "orderType", required = true)
    protected String orderType;
    @XmlAttribute(name = "caNumber")
    protected String caNumber;
    @XmlAttribute(name = "shortName", required = true)
    protected String shortName;

    /**
     * Gets the value of the users property.
     * 
     * @return
     *     possible object is
     *     {@link UsersType }
     *     
     */
    public UsersType getUsers() {
        return users;
    }

    /**
     * Sets the value of the users property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsersType }
     *     
     */
    public void setUsers(UsersType value) {
        this.users = value;
    }

    /**
     * Gets the value of the vms property.
     * 
     * @return
     *     possible object is
     *     {@link VmsType }
     *     
     */
    public VmsType getVms() {
        return vms;
    }

    /**
     * Sets the value of the vms property.
     * 
     * @param value
     *     allowed object is
     *     {@link VmsType }
     *     
     */
    public void setVms(VmsType value) {
        this.vms = value;
    }

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

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
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the caNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaNumber() {
        return caNumber;
    }

    /**
     * Sets the value of the caNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaNumber(String value) {
        this.caNumber = value;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

	@Override
	public String toString() {
		return "OrganizationType [users=" + users + ", vms=" + vms + ", orderId=" + orderId + ", name=" + name
				+ ", orderType=" + orderType + ", caNumber=" + caNumber + ", shortName=" + shortName + ", getUsers()="
				+ getUsers() + ", getVms()=" + getVms() + ", getOrderId()=" + getOrderId() + ", getName()=" + getName()
				+ ", getOrderType()=" + getOrderType() + ", getCaNumber()=" + getCaNumber() + ", getShortName()="
				+ getShortName() + "]";
	}
 
}