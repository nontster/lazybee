package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="External network not found")  // 404
public class ExternalNetworkNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -747266476021593844L;

	public ExternalNetworkNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
