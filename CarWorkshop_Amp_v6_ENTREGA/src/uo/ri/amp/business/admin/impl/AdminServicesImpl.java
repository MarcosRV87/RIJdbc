package uo.ri.amp.business.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;


/*
 * Implementación de la interfaz AdminServices
 * en la que se gestionan los diferentes Transaction Scripts.
 */
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
	public void deleteMechanic(Long id) throws BusinessException  {
		new DeleteMechanic(id).execute();
	}

	@Override
	public List<Map<String, Object>> listMechanics() {
		return new ListMechanics().execute();
	}
	
	@Override
	public List<Map<String, Object>> listActiveMechanics(){
		return new ListActiveMechanics().execute();
	}

	@Override
	public void addContratoMecanico(Long idMecanico, Date fechaCont, Date fechaExt,
			Double salarioBBA, Long tipo_contrato_id,
			Long categoria_id) throws BusinessException {
		new AddContractMechanic(idMecanico, fechaCont, fechaExt, salarioBBA, tipo_contrato_id, categoria_id).execute();
		
	}

	@Override
	public void deleteContratoMechanic(Long idContrato) throws BusinessException {
		new DeleteContractMechanic(idContrato).execute();
		
	}

	@Override
	public void updateContratoMecanico(Long idContrato, Date fechaCont,
			Date fechaExt, Double salarioBBA, Long tipo_contrato_id,
			Long categoria_id) throws BusinessException {
		new UpdateContractMechanic(idContrato, fechaCont, fechaExt, salarioBBA, tipo_contrato_id, categoria_id).execute();
		
	}

	@Override
	public List<Map<String, Object>> listNominasByMechanic(Long idMecanico) throws BusinessException {
		return new ListNominasByMechanic(idMecanico).execute();
	}

	@Override
	public void deleteLastNominaMecanico(Long idMecanico) throws BusinessException {
		new DeleteLastNominaMechanic(idMecanico).execute();
	}

	@Override
	public void addTipoContrato(String nombre, int num_dias) {
		new AddTipoContrato(nombre, num_dias).execute();		
	}

	@Override
	public void deleteTipoContrato(Long idTipoContrato) throws BusinessException {
		new DeleteTipoContrato(idTipoContrato).execute();
	}

	@Override
	public void updateTipoContrato(Long idTipoContrato, String nombre,
			int num_dias, Date fecha_fin) throws BusinessException {
		new UpdateTipoContrato(idTipoContrato, nombre, num_dias, fecha_fin).execute();
	}

	@Override
	public Map<String, Object> nominaInDetail(Long idNomina) throws BusinessException {
		return new NominaInDetail(idNomina).execute();
	}

	@Override
	public List<Map<String, Object>> listMechanicsByTipoContrato(
			Long idTipoContrato) throws BusinessException {
		return new ListMechanicsByTipoContrato(idTipoContrato).execute();
	}

	@Override
	public List<Map<String, Object>> listAllNominas() throws BusinessException {
		return new ListAllNominas().execute();
	}

	@Override
	public List<Map<String, Object>> listContractMechanic(Long idMecanico) throws BusinessException {
		return new ListContractMechanic(idMecanico).execute();
	}

	@Override
	public void finishContractMechanicAction(Long idMecanico) throws BusinessException {
		new FinishContractMechanic(idMecanico).execute();
	}

}
