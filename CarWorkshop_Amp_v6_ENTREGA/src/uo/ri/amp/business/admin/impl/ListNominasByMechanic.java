package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ContratosGateway;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Mostrar todas las nominas de un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class ListNominasByMechanic {
	
	private Long idMecanico;
	Connection con = null;

	public ListNominasByMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}
	
	NominasGateway ng = PersistenceFactory.getNominasGateway();
	ContratosGateway cg = PersistenceFactory.getContratosGateway();
	MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();

	public List<Map<String, Object>> execute() throws BusinessException {
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			con = Jdbc.getConnection();
			ng.setConnection(con);
			cg.setConnection(con);
			mg.setConnection(con);
			
			Long encontrado = mg.existeMecanico(idMecanico);
			Checker.assertMecanicoExiste(encontrado);
			list = ng.verificarNominasMecanico(idMecanico);
			Checker.assertTieneNominasMecanico(list);
			
			list = ng.listNominasByMechanic(idMecanico);
			
			return list;
			
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
		
	}
	
	

}
