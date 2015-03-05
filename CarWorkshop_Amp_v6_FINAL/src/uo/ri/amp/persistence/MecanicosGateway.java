package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/*
 * Interfaz referente al Table Data Gateway de
 * Mecánicos
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface MecanicosGateway {
	
	void setConnection(Connection con);
	void save(String nombre, String apellidos);
	void delete(Long id);
	void update(Long id, String nombre, String apellidos);
	List<Map<String,Object>> findAll();
	List<Map<String, Object>> findAllActives();
	Long existeMecanico(Long id);
	
}
