package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/*
 * Interfaz referente al Table Data Gateway de
 * Averías
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface AveriasGateway {
	
	void setConnection(Connection con);
    List<Map<String, Object>> comprobarAveriasPorMecanico(Long idMecanico) throws RuntimeException;
	List<Map<String, Object>> verificarAveriasDuranteContrato(Long idContrato);
    
}
