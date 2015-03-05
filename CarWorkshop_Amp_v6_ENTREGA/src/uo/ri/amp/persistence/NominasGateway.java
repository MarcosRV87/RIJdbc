package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/*
 * Interfaz referente al Table Data Gateway de
 * Nóminas
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface NominasGateway {

	void setConnection(Connection con);

	List<Map<String, Object>> verificarNominasMecanico(Long idMecanico)
			throws RuntimeException;

	List<Map<String, Object>> listNominasByMechanic(Long idMecanico);

	void deleteLastNominaMechanic(Long idMecanico);

	Long existeNomina(Long idNomina);

	Map<String, Object> nominaInDetail(Long idNomina);

	List<Map<String, Object>> listAllNominas();

	int getNumNominasByContrato(Long idContrato);

	List<Map<String, Object>> getNominasByContrato(Long idContrato);

}
