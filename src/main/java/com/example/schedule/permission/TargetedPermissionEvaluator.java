package com.example.schedule.permission;

import org.springframework.security.access.PermissionEvaluator;

public interface TargetedPermissionEvaluator extends PermissionEvaluator {
	String getTargetType();
}