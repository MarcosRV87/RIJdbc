package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.CategoriasGateway;
import uo.ri.amp.persistence.ContratosGateway;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Añadir Contrato a un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class AddContractMechanic {
	
	private Long idMecanico;
	private Date fechaCont;
	private Date fechaExt;
	private Double salarioBBA;
	private Long tipo_contrato_id;
	private Long categoria_id;
	
	Connection con = null;
	
	
	public AddContractMechanic(Long idMecanico, Date fechaCont2, Date fechaExt2,
			Double salarioBBA, Long tipo_contrato_id,
			Long categoria_id) {
		this.idMecanico = idMecanico;
		this.fechaCont = fechaCont2;
		this.fechaExt = fechaExt2;

		this.salarioBBA = salarioBBA;
		this.tipo_contrato_id = tipo_contrato_id;
		this.categoria_id = categoria_id;
	}

	public void execute() throws BusinessException {
		
		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();
		CategoriasGateway catg = PersistenceFactory.getCategoriasGateway();
		
		
		try {
			con = Jdbc.getConnection();
			con.setAutoCommit(false);
			cg.setConnection(con);
			mg.setConnection(con);
			tcg.setConnection(con);
			catg.setConnection(con);
			
			//Comprobaciones BBDD
			Long mecanico = mg.existeMecanico(idMecanico);
			Checker.assertMecanicoExiste(mecanico);
			Long tipocontrato = tcg.existeTipoContrato(tipo_contrato_id);
			Checker.assertTipoContratoExiste(tipocontrato);
			Long categoria = catg.existeCategoria(categoria_id);
			Checker.assertCategoriaExiste(categoria);
			
			//Tratamiento
			Long idContrato = cg.buscarContratosActivos(idMecanico); 
			cg.updateContratoaActivo(idContrato);
			cg.addContratoMecanico(idMecanico, fechaCont, fechaExt, salarioBBA, tipo_contrato_id, categoria_id);
			
			con.commit();
		} catch (SQLException e) {
			Checker.rollback(con);
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} catch (RuntimeException re){
			Checker.rollback(con);
			throw re;
		} finally {
			Jdbc.close(con);
		}

		
		
		
	}
	
	

}
