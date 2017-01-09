package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Missing VM template")  // 404
public class MissingVMTemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8899066370805521146L;

	public MissingVMTemplateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
