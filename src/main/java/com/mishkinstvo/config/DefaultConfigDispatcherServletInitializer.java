package com.mishkinstvo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DefaultConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{
			DefaultRootContext.class,
			DefaultSecurityContext.class
		};
	}

	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
