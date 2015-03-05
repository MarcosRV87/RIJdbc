package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.CategoriasGateway;
import uo.ri.amp.persistence.ContratosGateway;
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Modificar contrato a un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class UpdateContractMechanic {
	
	private Long idContrato;
	private Date fechaCont;
	private Date fechaExt;
	private Double salarioBBA;
	private Long tipo_contrato_id;
	private Long categoria_id;
	
	Connection con = null;

	public UpdateContractMechanic(Long idContrato, Date fechaCont,
			Date fechaExt, Double salarioBBA, Long tipo_contrato_id,
			Long categoria_id) {
		this.idContrato = idContrato;
		this.fechaCont = fechaCont;
		this.fechaExt = fechaExt;
		this.salarioBBA = salarioBBA;
		this.tipo_contrato_id = tipo_contrato_id;
		this.categoria_id = categoria_id;
	}
	
	public void execute() throws BusinessException{
		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();
		CategoriasGateway catg = PersistenceFactory.getCategoriasGateway();
		try {
			con = Jdbc.getConnection();
			cg.setConnection(con);
			tcg.setConnection(con);
			catg.setConnection(con);
			
			//Comprobaciones BBDD
			Long encontrado = cg.existeContrato(idContrato);
			Checker.assertContratoExiste(encontrado);
			encontrado = tcg.existeTipoContrato(tipo_contrato_id);
			Checker.assertTipoContratoExiste(encontrado);
			encontrado = catg.existeCategoria(categoria_id);
			Checker.assertCategoriaExiste(encontrado);
			
			cg.updateContratoMecanico(idContrato, fechaCont, fechaExt, salarioBBA, tipo_contrato_id, categoria_id);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
		
		
	}

}
