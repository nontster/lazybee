package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid template")  // 404
public class InvalidTemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9187048811913670379L;

	public InvalidTemplateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
