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
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Eliminar Contrato a un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class DeleteContractMechanic {

	private Long idContrato;
	Connection con = null;

	public DeleteContractMechanic(Long idContrato) {
		this.idContrato = idContrato;
	}
	
	public void execute() throws BusinessException{
		
		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		AveriasGateway ag = PersistenceFactory.getAveriasGateway();
		List<Map<String, Object>> list;
		try {
			con = Jdbc.getConnection();
			cg.setConnection(con);
			ag.setConnection(con);
			
			//Comprobaciones BBDD
			Long encontrado = cg.existeContrato(idContrato);
			Checker.assertContratoExiste(encontrado);
			list = ag.verificarAveriasDuranteContrato(idContrato);
			Checker.assertNoTieneAveriasDuranteContrato(list);
			
			cg.deleteContratoMecanico(idContrato);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}

		
	}

}
