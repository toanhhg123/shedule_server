package com.example.schedule.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.schedule.role.ERole;

@Aspect
@Component
public class AdminAuthorizationAspect {

	@Before("@annotation(com.example.schedule.annotation.IsAdmin)")
	public void checkAdminAccess() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority().equals(ERole.ROLE_ADMIN.name()))) {
			throw new AccessDeniedException("Fobbiden");
		}
	}
}