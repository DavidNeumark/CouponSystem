package com.dcs.couponSystem;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Coupon;
import exceptions.CouponSystemException;
import enumPackage.CouponType;
import facades.CustomerFacade;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "Customer")
public class CustomerResources {


	@RequestMapping(value = "/getAllPurchasedCouponsByType/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllPurchasedCouponsByType(@PathVariable("type") CouponType type,
			HttpServletRequest request, HttpServletResponse response) throws SQLException, CouponSystemException {

		CustomerFacade f = (CustomerFacade) request.getSession().getAttribute("session");

		// CustomerFacade f = (CustomerFacade)
		// CouponSystem.getInstance().login("customer", "1234", ClientType.CUSTOMER);
		if (f != null) {
			Collection<Coupon> coreResult = f.getAllPurchasedCouponByType(type);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getAllPurchasedCouponsByPrice/{price}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@PathVariable("price") double price,
			HttpServletRequest request, HttpServletResponse response) throws SQLException, CouponSystemException {

		CustomerFacade f = (CustomerFacade) request.getSession().getAttribute("session");

		// CustomerFacade f = (CustomerFacade)
		// CouponSystem.getInstance().login("customer", "1234", ClientType.CUSTOMER);
		if (f != null) {
			Collection<Coupon> coreResult = f.getAllPurchasedCouponPrice(price);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getAllPurchasedCoupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllPurchasedCoupons(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		CustomerFacade f = (CustomerFacade) request.getSession().getAttribute("session");

		// CustomerFacade f = (CustomerFacade)
		// CouponSystem.getInstance().login("customer", "1234", ClientType.CUSTOMER);
		if (f != null) {
			Collection<Coupon> coreResult = f.getAllPurchasedCoupons();
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getAllCouponsThatWereNotPurchased", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCouponsThatWereNotPurchased(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		CustomerFacade f = (CustomerFacade) request.getSession().getAttribute("session");

		// CustomerFacade f = (CustomerFacade)
		// CouponSystem.getInstance().login("customer", "1234", ClientType.CUSTOMER);
		if (f != null) {
			Collection<Coupon> coreResult = f.getAllCouponsThatWereNotPurchased();
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getAllSystemCoupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllSystemCoupons(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		CustomerFacade f = (CustomerFacade) request.getSession().getAttribute("session");

		// CustomerFacade f = (CustomerFacade)
		// CouponSystem.getInstance().login("customer", "1234", ClientType.CUSTOMER);
		if (f != null) {
			Collection<Coupon> coreResult = f.getAllSystemCoupons();
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/purchaseCoupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void purchaseCoupon(@RequestBody Coupon coupon, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		CustomerFacade f = (CustomerFacade) request.getSession().getAttribute("session");

		// CustomerFacade f = (CustomerFacade)
		// CouponSystem.getInstance().login("customer", "1234", ClientType.CUSTOMER);
		if (f != null) {
			f.purchaseCoupon(coupon);
		}
	}

}
