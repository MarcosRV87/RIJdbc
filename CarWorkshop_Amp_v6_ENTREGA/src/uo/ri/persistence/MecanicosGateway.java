package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {
	
	void setConnection(Connection con);
	void save(String nombre, String apellidos);
	void delete(Long id);
	void update(Long id, String nombre, String apellidos);
	List<Map<String,Object>> findAll();
	
}
