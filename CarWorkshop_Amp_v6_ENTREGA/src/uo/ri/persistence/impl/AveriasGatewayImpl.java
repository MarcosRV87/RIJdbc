package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uo.ri.common.BusinessException;
import uo.ri.persistence.AveriasGateway;
import alb.util.jdbc.Jdbc;

public class AveriasGatewayImpl implements AveriasGateway{
	

	private static final String SQL_IMPORTE_MANO_OBRA = "select sum(i.minutos * tv.precioHora / 60) "
			+ "	from TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv"
			+ "	where i.averia_id = a.id "
			+ "		and a.vehiculo_id = v.id"
			+ "		and v.tipo_id = tv.id"
			+ "		and a.id = ?"
			+ "		and a.status = 'TERMINADA'";

	private static final String SQL_VINCULAR_AVERIA_FACTURA = "update TAverias set factura_id = ? where id = ?";

	private static final String SQL_ACTUALIZAR_ESTADO_AVERIA = "update TAverias set status = ? where id = ?";

	private static final String SQL_VERIFICAR_ESTADO_AVERIA = "select status from TAverias where id = ?";

	private static final String SQL_UPDATE_IMPORTE_AVERIA = "update TAverias set importe = ? where id = ?";

	Connection conn;
	PreparedStatement pst = null;
	ResultSet rs = null;


	@Override
	public void setConnection(Connection con) {
		this.conn = con;
	}

	@Override
	public void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException {

		try {
			pst = conn.prepareStatement(SQL_VERIFICAR_ESTADO_AVERIA);
			
			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);
				
				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}
				
				String status = rs.getString(1); 
				if (! "TERMINADA".equalsIgnoreCase(status) ) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}
				
				rs.close();
			}
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public double consultaImporteManoObra(Long idAveria) throws SQLException, BusinessException {
		
		try {
			pst = conn.prepareStatement(SQL_IMPORTE_MANO_OBRA);
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}
			
			return rs.getDouble(1);
			
		} catch (BusinessException e) {
			throw e;
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
		try {
			pst = conn.prepareStatement(SQL_UPDATE_IMPORTE_AVERIA);
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		}	
		finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public void vincularAveriasConFactura(long idFactura, List<Long> idsAveria)
			throws SQLException {
		try {
			pst = conn.prepareStatement(SQL_VINCULAR_AVERIA_FACTURA);

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idFactura);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public void cambiarEstadoAverias(List<Long> idsAveria, String status)
			throws SQLException {
		try {
			pst = conn.prepareStatement(SQL_ACTUALIZAR_ESTADO_AVERIA);
			
			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
		
	}

}
