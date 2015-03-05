package uo.ri.business.cash.impl;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.business.cash.CashServices;

public class CashServicesImpl implements CashServices {

	@Override
	public Map<String, Object> facturarReparaciones(List<Long> idsAveria) throws BusinessException {
		// TODO Auto-generated method stub
		return new FacturarReparaciones(idsAveria).execute();
	}

}
