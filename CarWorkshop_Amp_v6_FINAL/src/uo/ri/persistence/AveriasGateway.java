package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.common.BusinessException;

public interface AveriasGateway {
	
	void setConnection(Connection con);
	void verificarAveriasTerminadas(List<Long >idsAveria) throws SQLException, BusinessException;
    double consultaImporteManoObra(Long idAveria) throws SQLException, BusinessException;
    void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException;
    void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException;
    void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException;
}
