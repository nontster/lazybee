package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Specified User's role not found")  // 404
public class UserRoleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9123892419477314030L;

	public UserRoleNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
