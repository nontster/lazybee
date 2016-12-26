package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Insufficient IP addresses")  // 404
public class InsufficientIPAddressesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2759991456827582740L;

	public InsufficientIPAddressesException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
