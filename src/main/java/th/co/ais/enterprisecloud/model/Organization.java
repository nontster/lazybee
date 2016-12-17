package th.co.ais.enterprisecloud.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement
public class Organization extends ResourceSupport {
	private String name;
	private String description;
	private String fullName;
	
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organization(String name, String description, String fullName) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setFullName(fullName);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}