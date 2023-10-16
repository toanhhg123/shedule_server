package com.example.schedule.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		logger.error("Unauthorized error: {}", authException.getMessage());
		resolver.resolveException(request, response, null, authException);

		// or use handle old
		// response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// // final Map<String, Object> body = new HashMap<>();
		// // body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		// // body.put("error", "Unauthorized");
		// // body.put("message", authException.getMessage());
		// // body.put("path", request.getServletPath());
		// ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, null, "No
		// Authencation",
		// authException.getMessage(), null);
		// final ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(response.getOutputStream(), apiError);

	}

}
