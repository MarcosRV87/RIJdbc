package uo.ri.business.cash;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashServices {

	Map<String,Object> facturarReparaciones(List<Long> idsAveria) throws BusinessException;
}
