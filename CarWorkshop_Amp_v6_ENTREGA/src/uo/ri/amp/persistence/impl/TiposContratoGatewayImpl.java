package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.TiposContratoGateway;

/*
 * Implementación referente al Table Data Gateway de
 * Tipos de Contrato
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class TiposContratoGatewayImpl implements TiposContratoGateway {

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public Long existeTipoContrato(Long id) {

		try {
			pst = con.prepareStatement(Conf.get("SQL_TIPO_CONTRATO_EXISTE"));
			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1);
			}
			return (long) 0;

		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando tipo contrato.");
		}

	}

	@Override
	public void addTipoContrato(String nombre, int num_dias) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_ADD_TIPO_CONTRATO"));
			pst.setString(1, nombre);
			pst.setInt(2, num_dias);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL añadiendo tipo de contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void deleteTipoContrato(Long idTipoContrato) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_TIPO_CONTRATO"));
			pst.setLong(1, idTipoContrato);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL borrando tipo de contrato.");
		}
	}

	@Override
	public void updateTipoContrato(Long idTipoContrato, String nombre,
			int num_dias, Date fecha_fin) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_UPDATE_TIPO_CONTRATO"));
			pst.setString(1, nombre);
			pst.setInt(2, num_dias);
			pst.setDate(3, new java.sql.Date(fecha_fin.getTime()));
			pst.setLong(4, idTipoContrato);
			

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL modificando tipo de contrato.");
		}

	}

	@Override
	public List<Map<String, Object>> listMechanicsByTipoContrato(
			Long idTipoContrato) {
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf.get("SQL_LIST_MECANICOS_BY_TIPO_CONTRATO"));
			pst.setLong(1, idTipoContrato);

			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("nombre", rs.getString(2));
				m.put("apellidos", rs.getString(3));
				m.put("salario_bba", rs.getDouble(4));

				res.add(m);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL listando mecanicos por tipo contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public int getNumDiasContrato(Long idContrato) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_GET_NUM_DIAS_CONTRATO"));
			pst.setLong(1, idContrato);
			
			rs = pst.executeQuery();

			rs.next();
			
			return (int) rs.getDouble(1);

		} catch (SQLException e) {
			throw new RuntimeException("Error SQL cogiendo num_dias de contrato.");
		}
	}

}
