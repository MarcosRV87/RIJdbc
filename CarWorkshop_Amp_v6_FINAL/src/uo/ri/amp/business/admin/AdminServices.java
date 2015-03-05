package uo.ri.amp.business.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.amp.common.BusinessException;

/*
 * Interfaz creada con el propósito de utilizar un
 * patrón Fachada de manera que los cambios realizados
 * en las capas más internas solo se verán
 * modificados en esta capa.
 * En este caso, se proporcionan los servicios del Administrador
 * en las que se gestionan los diferentes Transaction Scripts.
 */
public interface AdminServices {

	void addMechanic(String nombre, String apellidos) throws BusinessException;

	void updateMechanic(Long id, String nombre, String apellidos)
			throws BusinessException;

	void deleteMechanic(Long id) throws BusinessException;

	List<Map<String, Object>> listMechanics();

	List<Map<String, Object>> listActiveMechanics();

	void addContratoMecanico(Long idMecanico, Date fechaCont, Date fechaExt,
			Double salarioBBA, Long tipo_contrato_id, Long categoria_id)
			throws BusinessException;

	void deleteContratoMechanic(Long idMecanico) throws BusinessException;

	void updateContratoMecanico(Long idContrato, Date fechaCont, Date fechaExt,
			Double salarioBBA, Long tipo_contrato_id, Long categoria_id)
			throws BusinessException;

	List<Map<String, Object>> listNominasByMechanic(Long idMecanico)
			throws BusinessException;

	void deleteLastNominaMecanico(Long idMecanico) throws BusinessException;

	void addTipoContrato(String nombre, int num_dias);

	void deleteTipoContrato(Long idTipoContrato) throws BusinessException;

	void updateTipoContrato(Long idTipoContrato, String nombre, int num_dias,
			Date fecha_fin) throws BusinessException;

	Map<String, Object> nominaInDetail(Long idNomina) throws BusinessException;

	List<Map<String, Object>> listMechanicsByTipoContrato(Long idTipoContrato)
			throws BusinessException;

	List<Map<String, Object>> listAllNominas() throws BusinessException;

	List<Map<String, Object>> listContractMechanic(Long idMecanico)
			throws BusinessException;

	void finishContractMechanicAction(Long idContrato) throws BusinessException;

}
