package com.example.schedule.permission;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.example.schedule.auth.UserDetailsImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Data
public class AuthPermissionEvaluator implements TargetedPermissionEvaluator {

	@Override
	public String getTargetType() {
		return UserDetailsImpl.class.getSimpleName();
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (targetDomainObject != null
				&& UserDetailsImpl.class.isAssignableFrom(targetDomainObject.getClass())) {
			UserDetailsImpl domainObject = (UserDetailsImpl) targetDomainObject;
			return domainObject.getAuthorities().contains(permission);
		}

		return false;
	}

	@Override
	public boolean hasPermission(
			Authentication authentication,
			Serializable targetId,
			String targetType,
			Object permission) {

		return hasPermission(authentication, targetType, permission.toString());
	}

	public boolean hasPermission(
			Authentication authentication,
			String targetType,
			String permission) {

		throw new UnsupportedOperationException(
				"Not supported by this PermissionEvaluator: " + UserDetailsImpl.class);
	}
}
