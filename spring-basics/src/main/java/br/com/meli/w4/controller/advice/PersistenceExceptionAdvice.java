package br.com.meli.w4.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import exception.RepositoryException;

@RestControllerAdvice
public class PersistenceExceptionAdvice {

	
	@Autowired
	private MessageSource messageSource; // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/MessageSource.html
	
	@ExceptionHandler(value = RepositoryException.class)
	protected ResponseEntity<Object> handlePersistencia(RepositoryException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}
	
	
	@ExceptionHandler(value = NullPointerException.class)
	protected ResponseEntity<Object> handleNullPointer(NullPointerException ex, WebRequest request) {
		//String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body("usuario... o pessoal do desenvolvimento vai passar no rh.");
	}
	
	@ExceptionHandler(value = ArithmeticException.class)
	protected ResponseEntity<Object> erroCalculo(ArithmeticException ex, WebRequest request) {
		//String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body("erro de calculo... deu certo");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
