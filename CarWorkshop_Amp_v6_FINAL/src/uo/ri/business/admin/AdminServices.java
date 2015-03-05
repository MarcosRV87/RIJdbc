package uo.ri.business.admin;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AdminServices {
	
	void addMechanic(String nombre, String apellidos) throws BusinessException;
	void updateMechanic(Long id, String nombre, String apellidos) throws BusinessException;
	void deleteMechanic(Long id) throws BusinessException;
	List<Map<String,Object>> listMechanics();

}
