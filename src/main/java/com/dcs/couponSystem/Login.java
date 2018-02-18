package com.dcs.couponSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import exceptions.CouponSystemException;
import enumPackage.ClientType;
import facades.CouponClientFacade;

@Controller
public class Login extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public @ResponseBody String doGet() {
		return "Please use POST!";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public @ResponseBody void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");

		ClientType clientType = ClientType.convertToClientType(userType);

		CouponClientFacade facade;
		try {
			
			facade = CouponSystem.getInstance().login(username, password, clientType);
			PrintWriter out = response.getWriter();
			out.append("Succeed? " + (facade != null));
			System.out.println("Succeed? " + (facade != null));
			if (facade == null) {
				RequestDispatcher disp = request.getRequestDispatcher("/ErrorServlet");
				disp.include(request, response);
				
			} else {
				HashMap<String, String> urls = new HashMap<>();
				urls.put("ADMIN", "./Admin/index.html");
				urls.put("COMPANY", "./Company/index.html");
				urls.put("CUSTOMER", "./Customer/index.html");

				request.getSession().setAttribute("session", facade);
				request.getSession().setAttribute("authenticated", true);
				
				response.sendRedirect(urls.get(userType));

			}
		} catch (SQLException | CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}