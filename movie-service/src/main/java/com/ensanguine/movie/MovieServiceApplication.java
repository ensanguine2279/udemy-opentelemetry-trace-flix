package com.ensanguine.movie;

import org.h2.server.web.JakartaWebServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<JakartaWebServlet> h2ConsoleServletRegistration() {
		ServletRegistrationBean<JakartaWebServlet> registrationBean = new ServletRegistrationBean<>(
				new JakartaWebServlet(), "/h2-console/*");

		registrationBean.setName("H2Console");

		// Force H2 to allow connections originating outside container localhost
		registrationBean.addInitParameter("webAllowOthers", "true");
		registrationBean.setName("H2Console");

		return registrationBean;
	}
}
