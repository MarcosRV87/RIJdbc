package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.SustitucionesGateway;

public class SustitucionesGatewayImpl implements SustitucionesGateway {

	private Connection con;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	private static final String SQL_IMPORTE_REPUESTOS = 
			"select sum(s.cantidad * r.precio) " +
			"	from  TSustituciones s, TRepuestos r " +
			"	where s.repuesto_id = r.id " +
			"		and s.intervencion_averia_id = ?";
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	@Override
	public double consultaImporteRepuestos(Long idAveria) throws SQLException {
		
		
		try {
			pst = con.prepareStatement(SQL_IMPORTE_REPUESTOS);
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}
			
			return rs.getDouble(1);
			
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

}
