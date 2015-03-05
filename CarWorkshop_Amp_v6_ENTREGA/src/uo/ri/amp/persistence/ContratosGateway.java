package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * Interfaz referente al Table Data Gateway de
 * Contratos
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface ContratosGateway {

	void setConnection(Connection con);

	List<Map<String, Object>> verificarContratosMecanico(Long idMecanico)
			throws RuntimeException;

	void addContratoMecanico(Long idMecanico, Date fechaCont, Date fechaExt,
			Double salarioBBA, Long tipo_contrato_id, Long categoria_id);

	Long buscarContratosActivos(Long idMecanico);

	void updateContratoaActivo(Long idMecanico);

	void updateContratoaExtinto(Long idMecanico);

	void deleteContratoMecanico(Long idMecanico);

	Long existeContrato(Long idContrato);

	void updateContratoMecanico(Long idContrato, Date fechaCont, Date fechaExt,
			Double salarioBBA, Long tipo_contrato_id, Long categoria_id);

	List<Map<String, Object>> verificarContratosPorTipo(Long encontrado);

	List<Map<String, Object>> getContratosExtintosMecanico(Long idMecanico);

	Map<String, Object> getContratoActivoMecanico(Long idMecanico);

	void updateImporteExtincion(Long idContrato, double extincion);

}
