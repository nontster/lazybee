//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.29 at 01:39:40 PM ICT 
//


package th.co.ais.enterprisecloud.domain.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the th.co.ais.enterprisecloud.domain.response package. 
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

    private final static QName _Organization_QNAME = new QName("http://enterprisecloud.ais.co.th/bot", "organization");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: th.co.ais.enterprisecloud.domain.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrganizationType }
     * 
     */
    public OrganizationType createOrganizationType() {
        return new OrganizationType();
    }

    /**
     * Create an instance of {@link CredentialType }
     * 
     */
    public CredentialType createCredentialType() {
        return new CredentialType();
    }

    /**
     * Create an instance of {@link NatRulesType }
     * 
     */
    public NatRulesType createNatRulesType() {
        return new NatRulesType();
    }

    /**
     * Create an instance of {@link NatRuleType }
     * 
     */
    public NatRuleType createNatRuleType() {
        return new NatRuleType();
    }

    /**
     * Create an instance of {@link VmType }
     * 
     */
    public VmType createVmType() {
        return new VmType();
    }

    /**
     * Create an instance of {@link FirewallRulesType }
     * 
     */
    public FirewallRulesType createFirewallRulesType() {
        return new FirewallRulesType();
    }

    /**
     * Create an instance of {@link FirewallRuleType }
     * 
     */
    public FirewallRuleType createFirewallRuleType() {
        return new FirewallRuleType();
    }

    /**
     * Create an instance of {@link NetworkServicesType }
     * 
     */
    public NetworkServicesType createNetworkServicesType() {
        return new NetworkServicesType();
    }

    /**
     * Create an instance of {@link NatServiceType }
     * 
     */
    public NatServiceType createNatServiceType() {
        return new NatServiceType();
    }

    /**
     * Create an instance of {@link UsersType }
     * 
     */
    public UsersType createUsersType() {
        return new UsersType();
    }

    /**
     * Create an instance of {@link VmsType }
     * 
     */
    public VmsType createVmsType() {
        return new VmsType();
    }

    /**
     * Create an instance of {@link FirewallServiceType }
     * 
     */
    public FirewallServiceType createFirewallServiceType() {
        return new FirewallServiceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://enterprisecloud.ais.co.th/bot", name = "organization")
    public JAXBElement<OrganizationType> createOrganization(OrganizationType value) {
        return new JAXBElement<OrganizationType>(_Organization_QNAME, OrganizationType.class, null, value);
    }

}
