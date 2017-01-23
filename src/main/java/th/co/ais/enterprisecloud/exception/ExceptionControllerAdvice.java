package th.co.ais.enterprisecloud.exception;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler{

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ ExternalNetworkNotFoundException.class, MissingInputParameterException.class, InsufficientIPAddressesException.class,
			MissingVmTemplateException.class, UserRoleNotFoundException.class, VdcNetworkNotAvailableException.class })
	public @ResponseBody ExceptionResponse handleNotFoundException(HttpServletRequest request, RuntimeException ex) {
		ExceptionResponse response = new ExceptionResponse();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		response.setTimestamp(timestamp.getTime());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setPath(request.getServletPath()); 
		response.setMessage(ex.getMessage());

		return response;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({InvalidParameterException.class, InvalidTemplateException.class})
	public @ResponseBody ExceptionResponse handleBadRequestException(HttpServletRequest request, RuntimeException ex) {
		ExceptionResponse response = new ExceptionResponse();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		response.setTimestamp(timestamp.getTime());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setPath(request.getServletPath()); 
		response.setMessage(ex.getMessage());

		return response;
	}
	
}
