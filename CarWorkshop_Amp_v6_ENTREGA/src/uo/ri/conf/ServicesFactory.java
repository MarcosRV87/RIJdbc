package uo.ri.conf;

import uo.ri.business.admin.AdminServices;
import uo.ri.business.admin.impl.AdminServicesImpl;
import uo.ri.business.cash.CashServices;
import uo.ri.business.cash.impl.CashServicesImpl;

public class ServicesFactory {

	public static AdminServices createAdminServices() {
		return new AdminServicesImpl();
	}
	
	public static CashServices createCashServices() {
		return new CashServicesImpl();
	}
	
	

}
