package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Añadir mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class AddMechanic {

	private String nombre;
	private String apellidos;

	public AddMechanic(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() throws BusinessException {

		Connection c = null;
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();

		try {
			c = Jdbc.getConnection();
			mg.setConnection(c);
			mg.save(nombre, apellidos);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(c);
		}

	}

}
