package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Mostrar todos los mecánicos
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class ListMechanics {

	
	Connection con = null;
	
	public List<Map<String, Object>> execute() { 

		
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			con = Jdbc.getConnection();
			mg.setConnection(con);
			res = mg.findAll();
			
			return res;
			
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL listando mecánicos registrados.");
		}
		finally {
			Jdbc.close(con);
		}
	}
}
