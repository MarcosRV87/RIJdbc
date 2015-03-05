package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Ver nómina en detalle
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class NominaInDetail {

	private Long idNomina;

	Connection con;

	public NominaInDetail(Long idNomina) {
		this.idNomina = idNomina;
	}

	public Map<String, Object> execute() throws BusinessException {

		NominasGateway ng = PersistenceFactory.getNominasGateway();
		Map<String, Object> nomina = new HashMap<>();

		try {
			con = Jdbc.getConnection();
			ng.setConnection(con);

			Long encontrado = ng.existeNomina(idNomina);
			Checker.assertNominaExiste(encontrado);

			nomina = ng.nominaInDetail(idNomina);

			nomina = Checker.calcularSalariosNomina(nomina);

			return nomina;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}

	}

}
