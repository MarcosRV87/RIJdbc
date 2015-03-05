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

import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.ContratosGateway;
import alb.util.jdbc.Jdbc;

/*
 * Implementación referente al Table Data Gateway de
 * Contratos
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class ContratosGatewayImpl implements ContratosGateway {

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public List<Map<String, Object>> verificarContratosMecanico(Long idMecanico)
			throws RuntimeException {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> m = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf
					.get("SQL_VERIFICAR_CONTRATOS_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();
			while (rs.next()) {
				m.put("mecanico_id", rs.getLong(9));
				list.add(m);
			}

			return list;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL verificando contratos de mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void addContratoMecanico(Long idMecanico, Date fechaCont,
			Date fechaExt, Double salarioBBA, Long tipo_contrato_id,
			Long categoria_id) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_ADD_CONTRATO_MECANICO"));
			pst.setString(1, "ACTIVO");
			pst.setDate(2, new java.sql.Date(fechaCont.getTime()));
			pst.setDate(3, new java.sql.Date(fechaExt.getTime()));
			pst.setDouble(4, salarioBBA);
			pst.setLong(5, tipo_contrato_id);
			pst.setLong(6, categoria_id);
			pst.setLong(7, idMecanico);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL añadiendo contrato a mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	public Long buscarContratosActivos(Long idMecanico) {

		try {
			pst = con.prepareStatement(Conf
					.get("SQL_ID_CONTRATO_DE_MECANICOS_ACTIVOS"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1);
			}
			return (long) 0;
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando contratos activos.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	public void updateContratoaActivo(Long idMecanico) {
		try {
			pst = con.prepareStatement(Conf
					.get("SQL_UPDATE_CONTRATO_A_EXTINTO"));
			pst.setLong(1, idMecanico);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL modificando contrato extinto.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void deleteContratoMecanico(Long idMecanico) {
		try {
			pst = con
					.prepareStatement(Conf.get("SQL_DELETE_CONTRATO_MECANICO"));
			pst.setLong(1, idMecanico);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL borrando contrato mecanico.");
		}

	}

	@Override
	public Long existeContrato(Long idContrato) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_CONTRATO_EXISTE"));
			pst.setLong(1, idContrato);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1);
			}
			return (long) 0;

		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando contrato.");
		}
	}

	@Override
	public void updateContratoMecanico(Long idContrato, Date fechaCont,
			Date fechaExt, Double salarioBBA, Long tipo_contrato_id,
			Long categoria_id) {
		try {
			pst = con
					.prepareStatement(Conf.get("SQL_UPDATE_CONTRATO_MECANICO"));
			pst.setDate(1, new java.sql.Date(fechaCont.getTime()));
			pst.setDate(2, new java.sql.Date(fechaExt.getTime()));
			pst.setDouble(3, salarioBBA);
			pst.setLong(4, tipo_contrato_id);
			pst.setLong(5, categoria_id);
			pst.setLong(6, idContrato);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error SQL modificando contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public List<Map<String, Object>> verificarContratosPorTipo(Long idContrato) {
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf
					.get("SQL_VERIFICAR_CONTRATOS_POR_TIPO"));
			pst.setLong(1, idContrato);

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				res.add(m);
			}
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL Comprobando contratos por tipo contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<Map<String, Object>> getContratosExtintosMecanico(
			Long idMecanico) {
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			pst = con.prepareStatement(Conf
					.get("SQL_GET_CONTRATOS_EXTINTOS_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();

				m.put("id", rs.getLong(1));
				m.put("status", rs.getString(2));
				m.put("importe_extincion", rs.getDouble(5));
				res.add(m);
			}
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL cogiendo contratos extintos mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Map<String, Object> getContratoActivoMecanico(Long idMecanico) {
		Map<String, Object> m = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf
					.get("SQL_GET_CONTRATO_ACTIVO_MECANICO"));
			pst.setLong(1, idMecanico);

			rs = pst.executeQuery();

			if (rs.next()) {
				m.put("id", rs.getLong(1));
				m.put("status", rs.getString(2));
			}else{
				m.put("id", (long) 0);
			}
			
			return m;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL cogiendo contratos activo mecánico.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void updateImporteExtincion(Long idContrato, double extincion) {
		try {
			pst = con
					.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_EXTINCION"));
			pst.setDouble(1, extincion);
			pst.setLong(2, idContrato);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL modificando importe extinción contrato.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void updateContratoaExtinto(Long idMecanico) {
		try {
			pst = con.prepareStatement(Conf
					.get("SQL_UPDATE_CONTRATO_A_EXTINTO"));
			pst.setLong(1, idMecanico);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error SQL modificando contrato extinto.");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
