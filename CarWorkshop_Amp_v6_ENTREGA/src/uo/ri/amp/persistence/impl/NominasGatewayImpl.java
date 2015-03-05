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
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Implementación referente al Table Data Gateway de
 * Nóminas
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class NominasGatewayImpl implements NominasGateway {

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public List<Map<String, Object>> verificarNominasMecanico(Long idMecanico)
			throws RuntimeException {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_VERIFICAR_NOMINAS_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("id", rs.getLong(1));
				list.add(m);
			}
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL verificando nóminas de mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public List<Map<String, Object>> listNominasByMechanic(Long idMecanico) {
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf.get("SQL_LIST_NOMINAS_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("salario_base", rs.getDouble(2));
				m.put("contrato_id", rs.getLong(8));
				m.put("fecha", rs.getDate(9));

				res.add(m);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL listando nóminas de mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void deleteLastNominaMechanic(Long idMecanico) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_LAST_NOMINA_MECANICO"));
			pst.setLong(1, idMecanico);
			pst.setLong(2, idMecanico);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL borrando última nómina mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Long existeNomina(Long idNomina) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_NOMINA_EXISTE"));
			pst.setLong(1, idNomina);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1);
			}
			return (long) 0;

		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando nómina.");
		}
	}

	@Override
	public Map<String, Object> nominaInDetail(Long idNomina) {
		Map<String, Object> m = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_NOMINA_EN_DETALLE"));
			pst.setLong(1, idNomina);
			rs = pst.executeQuery();
			
			rs.next();
			m.put("id", rs.getLong(1));
			m.put("salario_base", rs.getDouble(2));
			m.put("pago_extra", rs.getDouble(3));
			m.put("importe_plus", rs.getDouble(4));
			m.put("importe_trienios", rs.getDouble(5));
			m.put("descuento_irpf", rs.getDouble(6));
			m.put("descuentoss", rs.getDouble(7));
			m.put("contrato_id", rs.getLong(8));
			m.put("fecha", rs.getDate(9));
			
			return m;
			
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando nómina.");
		}
	}

	@Override
	public List<Map<String, Object>> listAllNominas() {
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf.get("SQL_LIST_ALL_NOMINAS"));

			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("salario_base", rs.getDouble(2));
				m.put("pago_extra", rs.getDouble(3));
				m.put("importe_plus", rs.getDouble(4));
				m.put("importe_trienios", rs.getDouble(5));
				m.put("descuento_irpf", rs.getDouble(6));
				m.put("descuentoss", rs.getDouble(7));
				m.put("contrato_id", rs.getLong(8));
				m.put("fecha", rs.getDate(9));

				res.add(m);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL listando todas las nóminas.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public int getNumNominasByContrato(Long idContrato) {
		
		try {
			pst = con.prepareStatement(Conf
					.get("SQL_COUNT_NUM_NOMINAS_CONTRATO"));
			pst.setLong(1, idContrato);

			rs = pst.executeQuery();
			
			rs.next();
				
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL cogiendo número de nóminas de contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<Map<String, Object>> getNominasByContrato(Long idContrato) {
		
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf.get("SQL_GET_NOMINAS_CONTRATO"));
			pst.setLong(1, idContrato);

			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("salario_base", rs.getDouble(2));
				m.put("pago_extra", rs.getDouble(3));
				m.put("importe_plus", rs.getDouble(4));
				m.put("importe_trienios", rs.getDouble(5));
				m.put("descuento_irpf", rs.getDouble(6));
				m.put("descuentoss", rs.getDouble(7));
				m.put("contrato_id", rs.getLong(8));
				m.put("fecha", rs.getDate(9));

				res.add(m);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL cogiendo nóminas de mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
