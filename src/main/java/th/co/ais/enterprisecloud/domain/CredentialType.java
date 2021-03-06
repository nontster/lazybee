package th.co.ais.enterprisecloud.domain;

public class CredentialType {
	private String user;
	private String password;
	
	public CredentialType(String user, String password) {
		super();
		this.setUser(user);
		this.setPassword(password);
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}	
}
