package com.dcs.couponSystem;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import facades.AdminFacade;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "Admin")
public class AdminResources {

	final static Logger logger = Logger.getLogger(AdminResources.class);

	@RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Company> getAllCompanies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		logger.info("This is info : getAllCompanies call");
		if (f != null) {
			Collection<Company> coreResult = f.getAllCompanies();
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getCompany/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> getCompany(@PathVariable("id") long id, HttpServletRequest request,
			HttpServletResponse response) {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);

		if (f != null) {
			Company coreResult = new Company();
			try {
				coreResult = f.getCompany(id);
			} catch (CouponSystemException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

			}
			return new ResponseEntity<Company>(coreResult, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/createCompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCompany(@RequestBody Company company, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		// System.out.println(webCompany);
		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			f.createCompany(company);
		}
	}

	@RequestMapping(value = "/updateCompany", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCompany(@RequestBody Company company, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {
		// System.out.println(webCompany);
		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {

			f.updateCompany(company);
		}
	}

	@RequestMapping(value = "/deleteCompany", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCompany(@RequestBody Company company, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// System.out.println(webCompany);

		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			f.deleteCompany(company);
		}
	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody Customer customer, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			f.createCustomer(customer);
		}
	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@RequestBody Customer customer, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			f.updateCustomer(customer);
		}
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCustomer(@RequestBody Customer customer, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			f.removeCustomer(customer);
		}
	}

	@RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(@PathVariable("id") long id, HttpServletRequest request,
			HttpServletResponse response) throws CouponSystemException, SQLException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			Customer coreResult = f.getCustomer(id);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Customer> getAllCustomers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		AdminFacade f = (AdminFacade) request.getSession().getAttribute("session");
		// AdminFacade f = (AdminFacade)
		// CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		if (f != null) {
			Collection<Customer> coreResult = f.getAllCustomers();
			return coreResult;
		}
		return null;
	}

}
