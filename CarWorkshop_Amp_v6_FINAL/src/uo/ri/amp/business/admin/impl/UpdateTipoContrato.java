package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Modificar tipo de contrato
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class UpdateTipoContrato {
	
	private Long idTipoContrato;
	private String nombre;
	private int num_dias;
	private Date fecha_fin;
	
	Connection con;

	public UpdateTipoContrato(Long idTipoContrato, String nombre, int num_dias,
			Date fecha_fin) {
		this.idTipoContrato = idTipoContrato;
		this.nombre = nombre;
		this.num_dias = num_dias;
		this.fecha_fin = fecha_fin;
	}

	public void execute() throws BusinessException {
		
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();

		try {
			con = Jdbc.getConnection();
			tcg.setConnection(con);
			
			//Comprobaciones BBDD
			Long encontrado = tcg.existeTipoContrato(idTipoContrato);
			Checker.assertTipoContratoExiste(encontrado);
			
			tcg.updateTipoContrato(idTipoContrato, nombre, num_dias, fecha_fin);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
		
	}

}
