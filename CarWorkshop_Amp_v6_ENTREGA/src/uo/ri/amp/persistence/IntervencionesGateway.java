package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/*
 * Interfaz referente al Table Data Gateway de
 * Intervenciones
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface IntervencionesGateway {

	void setConnection(Connection con);

	List<Map<String, Object>> verificarIntervencionesPorMecanico(Long idMecanico)
			throws RuntimeException;
}
