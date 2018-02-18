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
import facades.CompanyFacade;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "Company")
public class CompanyResources {


	@RequestMapping(value = "/getAllCoupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {
		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);
		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");

		if (f != null) {
			Collection<Coupon> coreResult = f.getAllCoupons();
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getCoupon/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon getCoupon(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response)
			throws CouponSystemException, SQLException {

		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);
		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");

		if (f != null) {
			Coupon coreResult = f.getCoupon(id);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getCouponByType/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponByType(@PathVariable("type") CouponType type, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);
		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");

		if (f != null) {
			Collection<Coupon> coreResult = f.getCouponByType(type);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getCouponByDate/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponByType(@PathVariable("date") java.sql.Date date, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, CouponSystemException {

		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");
		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);

		if (f != null) {
			Collection<Coupon> coreResult = f.getCouponByDate(date);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/getCouponByPrice/{couponPrice}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponByType(@PathVariable("couponPrice") double couponPrice,
			HttpServletRequest request, HttpServletResponse response) throws SQLException, CouponSystemException {

		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");
		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);

		if (f != null) {
			Collection<Coupon> coreResult = f.getCouponByPrice(couponPrice);
			return coreResult;
		}
		return null;
	}

	@RequestMapping(value = "/createCoupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCoupon(@RequestBody Coupon coupon, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");
		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);

		if (f != null) {
			f.createCoupon(coupon);
		}
	}

	@RequestMapping(value = "/updateCoupon", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCoupon(@RequestBody Coupon coupon, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");
		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);

		// System.out.println(webCoupon);
		if (f != null) {
			f.updateCoupon(coupon);
		}
	}

	@RequestMapping(value = "/deleteCoupon", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCoupon(@RequestBody Coupon coupon, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, CouponSystemException {

		CompanyFacade f = (CompanyFacade) request.getSession().getAttribute("session");
		// CompanyFacade f = (CompanyFacade) CouponSystem.getInstance().login("company",
		// "1234", ClientType.COMPANY);

		if (f != null) {
			f.removeCoupon(coupon);
		}
	}

}
