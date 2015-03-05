package uo.ri.business.cash.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.SustitucionesGateway;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;

public class FacturarReparaciones {

	private Connection connection;
	private AveriasGateway agi;
	private FacturasGateway fgi;
	private SustitucionesGateway sgi;
	
	List<Long> idsAveria = new ArrayList<>();
	public FacturarReparaciones(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	public Map<String, Object> execute() throws BusinessException {

		agi = PersistenceFactory.getAveriasGateway();
		fgi = PersistenceFactory.getFacturasGateway();

		try {
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
			
			agi.setConnection(connection);
			agi.verificarAveriasTerminadas(idsAveria);
			
			fgi.setConnection(connection);

			long numeroFactura = fgi.generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			
			double totalFactura = 0.0;
			for(Long idAveria : idsAveria) {
				double importeManoObra = agi.consultaImporteManoObra(idAveria);
				double importeRepuestos = consultaImporteRepuestos(idAveria);
				double totalAveria = importeManoObra + importeRepuestos;
				
				actualizarImporteAveria(idAveria, totalAveria);
				
				totalFactura += totalAveria; 
			}
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva/100);
			importe = Round.twoCents(importe);
			
			long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");
 
			connection.commit();
			
			Map<String, Object> f = new HashMap<>();
			f.put("numeroFactura", numeroFactura);
			f.put("fechaFactura", fechaFactura);
			f.put("totalFactura", totalFactura);
			f.put("iva", iva);
			f.put("importe", importe);
			
			
			return f;
		}
		catch (SQLException e) {
			try { connection.rollback(); } catch (SQLException ex) {};
			throw new RuntimeException(e);
		}
		catch (BusinessException e) {
			try { connection.rollback(); } catch (SQLException ex) {};
			throw e;
		}
		finally {
			Jdbc.close(connection);
		}

	}


	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException {
		agi = PersistenceFactory.getAveriasGateway();
		agi.setConnection(connection);
		agi.cambiarEstadoAverias(idsAveria, status);
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException {
		agi = PersistenceFactory.getAveriasGateway();
		agi.setConnection(connection);
		agi.vincularAveriasConFactura(idFactura, idsAveria);
	}

	private long crearFactura(long numeroFactura, Date fechaFactura,
			double iva, double totalConIva) throws SQLException {
		fgi = PersistenceFactory.getFacturasGateway();
		fgi.setConnection(connection);
		return fgi.crearFactura(numeroFactura, fechaFactura, iva, totalConIva);	
	}


	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}


	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
		agi = PersistenceFactory.getAveriasGateway();
		agi.setConnection(connection);
		agi.actualizarImporteAveria(idAveria, totalAveria);
		
	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		sgi = PersistenceFactory.getSustitucionesGateway();
		sgi.setConnection(connection);
		return sgi.consultaImporteRepuestos(idAveria);
	}



}
