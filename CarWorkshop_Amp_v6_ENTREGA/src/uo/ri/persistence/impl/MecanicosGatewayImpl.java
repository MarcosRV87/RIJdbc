package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway {

	private Connection con;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	private static String SQL1 = "insert into TMecanicos(nombre, apellidos) "
			+ "values (?, ?)";
	private static String SQL2 = "delete from TMecanicos where id = ?";
	private static String SQL3 = "update TMecanicos "
			+ "set nombre = ?, apellidos = ? " + "where id = ?";
	private static String SQL4 = "select id, nombre, apellidos from TMecanicos";

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	public void save(String nombre, String apellidos) {

		try {
			pst = con.prepareStatement(SQL1);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void delete(Long id) {

		try {
			pst = con.prepareStatement(SQL2);
			pst.setLong(1, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public void update(Long id, String nombre, String apellidos) {

		try {
			pst = con.prepareStatement(SQL3);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public List<Map<String, Object>> findAll() {
		
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(SQL4);

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
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

}
