package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.MecanicosGateway;

/*
 * Implementación referente al Table Data Gateway de
 * Mecánicos
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class MecanicosGatewayImpl implements MecanicosGateway {

	private Connection con;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	public void save(String nombre, String apellidos) {

		try {
			pst = con.prepareStatement(Conf.get("SQL_ADD_MECANICO"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL añadiendo mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void delete(Long id) {

		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_MECANICO"));
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL borrando mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void update(Long id, String nombre, String apellidos) {

		try {
			pst = con.prepareStatement(Conf.get("SQL_UPDATE_MECANICO"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL modificando mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public List<Map<String, Object>> findAll() {

		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf.get("SQL_LIST_ALL_MECANICOS"));

			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("nombre", rs.getString(2));
				m.put("apellidos", rs.getString(3));

				res.add(m);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL listando mecánicos registrados");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public List<Map<String, Object>> findAllActives() {

		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf.get("SQL_MECANICOS_CONTRATO_EN_VIGOR"));

			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("nombre", rs.getString(2));
				m.put("apellidos", rs.getString(3));

				res.add(m);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL listando mecánicos activos.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Long existeMecanico(Long id) {

		try {
			pst = con.prepareStatement(Conf.get("SQL_MECANICO_EXISTE"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1);
			}
			return (long) 0;

		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando mecánico.");
		}

	}

}
