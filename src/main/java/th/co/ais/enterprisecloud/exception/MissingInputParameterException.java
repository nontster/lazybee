package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Missing paramter")  // 404
public class MissingParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3313370636816311317L;

	public MissingParameterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
