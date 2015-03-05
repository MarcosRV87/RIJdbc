package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Modificar mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class UpdateMechanic {

	private Long id;
	private String nombre;
	private String apellidos;
	Connection con = null;

	public UpdateMechanic(Long id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() throws BusinessException {

		
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();

		try {
			con = Jdbc.getConnection();
			mg.setConnection(con);
			Long encontrado = mg.existeMecanico(id);
			Checker.assertMecanicoExiste(encontrado);
			mg.update(id, nombre, apellidos);

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}

	}

}
