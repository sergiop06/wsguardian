package com.wsguardian.engine;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wsguardian.engine.servlets.EngineServlet;

@Configuration
public class WebConfig {
	
	@Bean
	public ServletRegistrationBean<HttpServlet> engineServlet(){
		ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
		servRegBean.setServlet(new EngineServlet());
		servRegBean.addUrlMappings("/service/*");
		servRegBean.setLoadOnStartup(1);
		return servRegBean;
	}

}
