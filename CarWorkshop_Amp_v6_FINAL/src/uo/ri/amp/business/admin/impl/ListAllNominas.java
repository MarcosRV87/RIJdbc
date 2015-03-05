package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Mostrar todas las nóminas generadas.
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class ListAllNominas {

	Connection con = null;
	
	public List<Map<String, Object>> execute() throws BusinessException {
		
		NominasGateway ng = PersistenceFactory.getNominasGateway();
		
		List<Map<String, Object>> res = new ArrayList<>();
		List<Map<String, Object>> nomina = new ArrayList<>();

		try {
			con = Jdbc.getConnection();
			ng.setConnection(con);
			
			res = ng.listAllNominas();
			Checker.assertExistenNominas(res);
			Map<String,Object> aux = new HashMap<>();
			
			for(Map<String,Object> m : res){
				aux = Checker.calcularSalariosNomina(m);
				nomina.add(aux);
			}
			
			return nomina;
			
		}  catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
	}

}
