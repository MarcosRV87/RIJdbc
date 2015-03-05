package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway{

	private Connection conn;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	private static final String SQL_ULTIMO_NUMERO_FACTURA = 
			"select max(numero) from TFacturas";
	
	private static final String SQL_INSERTAR_FACTURA = 
			"insert into TFacturas(numero, fecha, iva, importe, status) " +
			"	values(?, ?, ?, ?, ?)";
	
	private static final String SQL_RECUPERAR_CLAVE_GENERADA = 
			"select id from TFacturas where numero = ?";
	
	@Override
	public void setConnection(Connection con) {
		this.conn = con;
	}

	@Override
	public long generarNuevoNumeroFactura() throws SQLException {

		try {
			pst = conn.prepareStatement(SQL_ULTIMO_NUMERO_FACTURA);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, el siguiente
			} else {  // todavía no hay ninguna
				return 1L;
			}
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public long crearFactura(long numeroFactura, Date fechaFactura, double iva,
			double totalConIva) throws SQLException {
		try {
			pst = conn.prepareStatement(SQL_INSERTAR_FACTURA);
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();

			return getGeneratedKey(numeroFactura); // Id de la nueva factura generada
			
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public long getGeneratedKey(long numeroFactura) throws SQLException {
		// TODO Auto-generated method stub
		try {
			pst = conn.prepareStatement(SQL_RECUPERAR_CLAVE_GENERADA);
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);
			
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
