package voyago.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import voyago.backend.utils.ResponseWrapper;
import voyago.backend.utils.Status;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseWrapper<String>> userException(UserException ex) {
		return new ResponseEntity<>(new ResponseWrapper<String>(Status.FAILURE,"Error handling user",ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ResponseWrapper<String>> databaseException(DatabaseException ex) {
		return new ResponseEntity<>(new ResponseWrapper<String>(Status.FAILURE,"Server error",ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper<String>> genericException(Exception ex) {
		return new ResponseEntity<>(new ResponseWrapper<String>(Status.FAILURE,"Unexpected error occurred.",ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
