package com.example.schedule.permission;

import java.io.Serializable;
import java.util.Map;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.DenyAllPermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PermissionEvaluatorManager implements PermissionEvaluator {
	private static final PermissionEvaluator denyAll = new DenyAllPermissionEvaluator();
	private final Map<String, PermissionEvaluator> permissionEvaluators;

	public PermissionEvaluatorManager(Map<String, PermissionEvaluator> permissionEvaluators) {
		this.permissionEvaluators = permissionEvaluators;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		PermissionEvaluator permissionEvaluator = permissionEvaluators
				.get(targetDomainObject.getClass().getSimpleName());

		if (permissionEvaluator == null) {
			permissionEvaluator = denyAll;
		}

		return permissionEvaluator.hasPermission(authentication, targetDomainObject, permission);
	}

	@Override
	public boolean hasPermission(
			Authentication authentication, Serializable targetId,
			String targetType, Object permission) {
		PermissionEvaluator permissionEvaluator = permissionEvaluators.get(targetType);

		if (permissionEvaluator == null) {
			permissionEvaluator = denyAll;
		}

		return permissionEvaluator.hasPermission(authentication, targetId, targetType, permission);
	}

	private boolean hasPrivilege(Authentication auth, String targetType, String permission) {

		for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
			if (grantedAuth.getAuthority().startsWith(targetType) &&
					grantedAuth.getAuthority().contains(permission)) {
				return true;
			}
		}
		return false;
	}

}
