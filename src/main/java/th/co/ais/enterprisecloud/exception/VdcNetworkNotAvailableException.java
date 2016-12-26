package th.co.ais.enterprisecloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Specified Vdc network not available")  // 404
public class VdcNetworkNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6648104997845447245L;

	public VdcNetworkNotAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
