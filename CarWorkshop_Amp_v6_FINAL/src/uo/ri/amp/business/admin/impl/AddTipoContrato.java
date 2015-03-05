package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Añadir Tipo de Contrato
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class AddTipoContrato {
	
	private String nombre;
	private int num_dias;
	
	private Connection con;

	public AddTipoContrato(String nombre, int num_dias) {
		this.nombre = nombre;
		this.num_dias = num_dias;
	}
	
	public void execute(){
		
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();
		
		try {
			con = Jdbc.getConnection();
			tcg.setConnection(con);
			
			tcg.addTipoContrato(nombre, num_dias);
		
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
	}

}
