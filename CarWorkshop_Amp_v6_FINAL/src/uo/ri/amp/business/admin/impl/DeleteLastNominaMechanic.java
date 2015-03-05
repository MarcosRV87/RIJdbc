package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Eliminar la última nómina de un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class DeleteLastNominaMechanic {

	private Long idMecanico;
	Connection con = null;
	
	public DeleteLastNominaMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}
	
	public void execute() throws BusinessException{
		NominasGateway ng = PersistenceFactory.getNominasGateway();
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		List<Map<String,Object>> list;
		try {
			con = Jdbc.getConnection();
			ng.setConnection(con);
			mg.setConnection(con);
			
			//Comprobaciones BBDD
			Long encontrado = mg.existeMecanico(idMecanico);
			Checker.assertMecanicoExiste(encontrado);
			list = ng.verificarNominasMecanico(idMecanico);
			Checker.assertTieneNominasMecanico(list);
			
			ng.deleteLastNominaMechanic(idMecanico);
			
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
		
	}

}
