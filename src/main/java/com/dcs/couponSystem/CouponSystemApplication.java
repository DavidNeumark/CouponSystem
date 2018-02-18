package com.dcs.couponSystem;

import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CouponSystemApplication {
	
	private @Autowired AutowireCapableBeanFactory beanElement;

	public static void main(String[] args) {
		SpringApplication.run(CouponSystemApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {

		FilterRegistrationBean bean = new FilterRegistrationBean();
		Filter filter = new FilterCore();
		beanElement.autowireBean(filter);

		bean.setFilter(filter);
		bean.addUrlPatterns("/Admin/index.html");
		bean.addUrlPatterns("/Company/index.html");
		bean.addUrlPatterns("/Customer/index.html");

		return bean;
	}
	
}
