package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.AveriasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Implementación referente al Table Data Gateway de
 * Averías
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class AveriasGatewayImpl implements AveriasGateway {

	Connection conn;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection con) {
		this.conn = con;
	}

	@Override
	public List<Map<String, Object>> comprobarAveriasPorMecanico(Long idMecanico) {

		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = conn.prepareStatement(Conf
					.get("SQL_VERIFICAR_AVERIAS_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				res.add(m);
			}
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL Comprobando averias de mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<Map<String, Object>> verificarAveriasDuranteContrato(
			Long idContrato) {

		List<Map<String, Object>> list = new ArrayList<>();
		Date today = Calendar.getInstance().getTime();

		try {
			Map<String, Object> m = new HashMap<>();
			pst = conn.prepareStatement(Conf
					.get("SQL_VERIFICAR_AVERIAS_CONTRATO"));
			pst.setLong(1, idContrato);
			pst.setDate(2, new java.sql.Date(today.getTime()));

			rs = pst.executeQuery();
			while (rs.next()) {
				m.put("id", rs.getLong(1));
				list.add(m);
			}
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL Comprobando averias de contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
