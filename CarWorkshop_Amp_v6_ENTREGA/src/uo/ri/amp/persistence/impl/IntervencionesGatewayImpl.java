package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.IntervencionesGateway;
import alb.util.jdbc.Jdbc;

/*
 * Implementación referente al Table Data Gateway de
 * Intervenciones
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class IntervencionesGatewayImpl implements IntervencionesGateway {

	private Connection con;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public List<Map<String, Object>> verificarIntervencionesPorMecanico(
			Long idMecanico) throws RuntimeException {

		List<Map<String, Object>> list = new ArrayList<>();

		try {
			Map<String, Object> m = new HashMap<>();
			pst = con.prepareStatement(Conf
					.get("SQL_VERIFICAR_INTERVENCIONES_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();
			while (rs.next()) {
				m.put("id", rs.getLong(1));
				list.add(m);
			}
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL verificando intervenciones mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
