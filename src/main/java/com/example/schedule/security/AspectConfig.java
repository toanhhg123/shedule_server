package com.example.schedule.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.schedule.annotation.AdminAuthorizationAspect;

public class AspectConfig {
	public AdminAuthorizationAspect adminAuthorizationAspect() {
		return new AdminAuthorizationAspect();
	}
}
