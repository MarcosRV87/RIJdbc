package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AveriasGateway;
import uo.ri.amp.persistence.ContratosGateway;
import uo.ri.amp.persistence.IntervencionesGateway;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Borrar mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class DeleteMechanic {

	private Long idMecanico;
	Connection c = null;
	
	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() throws BusinessException  {

		
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		AveriasGateway ag = PersistenceFactory.getAveriasGateway();
		IntervencionesGateway ig = PersistenceFactory
				.getIntervencionesGateway();
		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		NominasGateway ng = PersistenceFactory.getNominasGateway();
		List<Map<String, Object>> list;
		try {
			c = Jdbc.getConnection();
			mg.setConnection(c);
			ag.setConnection(c);
			ig.setConnection(c);
			cg.setConnection(c);
			ng.setConnection(c);
			
			//Comprobaciones BBDD
			
			Long encontrado = mg.existeMecanico(idMecanico);
			Checker.assertMecanicoExiste(encontrado);
			list = cg.verificarContratosMecanico(idMecanico);
			Checker.assertNoTieneContratosMecanico(list);
			list = ig.verificarIntervencionesPorMecanico(idMecanico);
			Checker.assertNoTieneIntervencionesPorMecanico(list);
			list = ag.comprobarAveriasPorMecanico(idMecanico);
			Checker.assertNoTieneAveriasMecanico(list);
			
			list = ng.verificarNominasMecanico(idMecanico);
			Checker.assertNoTieneNominasMecanico(list);

			mg.delete(idMecanico);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(c);
		}

	}

	

}
