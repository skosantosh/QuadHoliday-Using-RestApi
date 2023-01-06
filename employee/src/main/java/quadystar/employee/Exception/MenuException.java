package quadystar.employee.Exception;

import org.springframework.http.HttpStatus;
public class MenuException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatusCode;
	private final String message;

	public MenuException(HttpStatus httpStatusCode, String message, Throwable throwable) {
		super(message, throwable);
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	public MenuException(HttpStatus httpStatusCode, String message) {
		super(message);
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

}
