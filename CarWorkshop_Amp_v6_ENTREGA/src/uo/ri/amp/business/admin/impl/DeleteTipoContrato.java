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
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Eliminar tipo de contrato
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class DeleteTipoContrato {

	private Long idTipoContrato;
	
	Connection con;
	
	public DeleteTipoContrato(Long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public void execute() throws BusinessException {
		
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();
		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		List<Map<String,Object>> list = new ArrayList<>();
		try {
			con = Jdbc.getConnection();
			tcg.setConnection(con);
			cg.setConnection(con);
			
			Long encontrado = tcg.existeTipoContrato(idTipoContrato);
			Checker.assertTipoContratoExiste(encontrado);
			list = cg.verificarContratosPorTipo(idTipoContrato);
			Checker.assertTipoContratoNoTieneContratos(list);
			
			tcg.deleteTipoContrato(idTipoContrato);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
	}

}
