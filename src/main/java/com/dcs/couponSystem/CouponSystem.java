package com.dcs.couponSystem;

import java.sql.SQLException;

import exceptions.CouponSystemException;
import enumPackage.ClientType;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CouponClientFacade;
import facades.CustomerFacade;

/**
 * @author D.Neumark
 *
 */
public class CouponSystem {

	private static CouponSystem instance;
	private DailyCouponExpirationTask dailyCouponExpirationTask;
	Thread dailyThread;

	private CouponSystem() throws CouponSystemException {

		dailyCouponExpirationTask = new DailyCouponExpirationTask();
		dailyThread = new Thread(dailyCouponExpirationTask);
		// dailyThread.start();

	}

	public static synchronized CouponSystem getInstance() throws CouponSystemException {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

	public CouponClientFacade login(String name, String password, ClientType clientType)
			throws SQLException, CouponSystemException {
		switch (clientType) {

		case ADMIN:
			AdminFacade adminFacade = new AdminFacade();
			return adminFacade.login(name, password, clientType);

		case COMPANY:
			CompanyFacade companyFacade = new CompanyFacade();
			return companyFacade.login(name, password, clientType);

		case CUSTOMER:
			CustomerFacade customerFacade = new CustomerFacade();
			return customerFacade.login(name, password, clientType);

		}
		return null;
	}

	public void shutdown() {

		// TODO turn off the daily task
		dailyCouponExpirationTask.stopTask();
		dailyThread.interrupt();
		// ConnectionPool couponConnPool = ConnectionPool.getInstance();
		// couponConnPool.closeAllConnections();
	}

}
