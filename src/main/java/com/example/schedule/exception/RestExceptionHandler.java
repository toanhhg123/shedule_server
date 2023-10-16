package com.example.schedule.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.schedule.security.jwt.AuthTokenFilter;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	@Nullable
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));

	}

	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	protected ResponseEntity<Object> handleException(
			Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler({ AuthenticationException.class })
	@ResponseBody
	public ResponseEntity<?> handleAuthenticationException(Exception ex) {

		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
		apiError.setMessage("no authencation");
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	protected ResponseEntity<Object> handleAccessDeniedException(
			Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
		if (ex.getMessage().equals("Fobbiden")) {
			apiError.setStatus(HttpStatus.FORBIDDEN);
		}
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(NoSuchElementFoundException.class)
	protected ResponseEntity<Object> handleItemNotFoundException(
			NoSuchElementFoundException ex,
			WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}