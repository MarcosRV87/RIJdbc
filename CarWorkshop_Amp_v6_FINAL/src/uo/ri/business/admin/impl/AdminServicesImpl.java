package uo.ri.business.admin.impl;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.business.admin.AdminServices;

public class AdminServicesImpl implements AdminServices {

	@Override
	public void addMechanic(String nombre, String apellidos) throws BusinessException {
		new AddMechanic(nombre, apellidos).execute();
	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) throws BusinessException {
		new UpdateMechanic(id, nombre, apellidos).execute();
	}

	@Override
	public void deleteMechanic(Long id) throws BusinessException {
		new DeleteMechanic(id).execute();
	}

	@Override
	public List<Map<String, Object>> listMechanics() {
		return new ListMechanics().execute();
	}

}
