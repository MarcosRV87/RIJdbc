package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * Interfaz referente al Table Data Gateway de
 * Tipos de Contrato
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface TiposContratoGateway {

	void setConnection(Connection con);
	Long existeTipoContrato(Long id);
	void addTipoContrato(String nombre, int num_dias);
	void deleteTipoContrato(Long idTipoContrato);
	void updateTipoContrato(Long idTipoContrato, String nombre, int num_dias,
			Date fecha_fin);
	List<Map<String, Object>> listMechanicsByTipoContrato(Long idTipoContrato);
	int getNumDiasContrato(Long long1);

	

}
