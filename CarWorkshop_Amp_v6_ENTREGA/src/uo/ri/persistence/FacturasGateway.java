package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public interface FacturasGateway {
	
	void setConnection(Connection con);

	long generarNuevoNumeroFactura() throws SQLException;

	long crearFactura(long numeroFactura, Date fechaFactura, double iva,
			double totalConIva) throws SQLException;
	
	long getGeneratedKey(long numeroFactura) throws SQLException;

}
