package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface SustitucionesGateway {

	public void setConnection(Connection con);
	public double consultaImporteRepuestos(Long idAveria) throws SQLException;
}
