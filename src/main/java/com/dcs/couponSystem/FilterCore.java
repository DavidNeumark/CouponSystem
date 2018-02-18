package com.dcs.couponSystem;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;

public class FilterCore implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest requ, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {

		boolean authenticated = false;

		HttpServletRequest request = (HttpServletRequest) requ;
		HttpSession session = request.getSession();

		if (session.getAttribute("authenticated") != null) {
			authenticated = (boolean) session.getAttribute("authenticated");
		}

		if (authenticated) {
			filterChain.doFilter(requ, resp);
		} else {
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendError(HttpStatus.FORBIDDEN.value());
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
