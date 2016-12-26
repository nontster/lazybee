package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid parameter")  // 404
public class InvalidParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3851506248233877653L;

	public InvalidParameterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
