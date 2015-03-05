package uo.ri.amp.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.impl.check.Checker;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Mostrar todos los mecánicos dependiendo del tipo de contrato
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class ListMechanicsByTipoContrato {
	
	private Long idTipoContrato;
	
	Connection con;

	public ListMechanicsByTipoContrato(Long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public List<Map<String, Object>> execute() throws BusinessException {
		
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();
		List<Map<String,Object>> list = new ArrayList<>();
		double acumulado = 0.0;
		try {
			con = Jdbc.getConnection();
			tcg.setConnection(con);
			mg.setConnection(con);
			
			Long encontrado = tcg.existeTipoContrato(idTipoContrato);
			Checker.assertTipoContratoExiste(encontrado);
			
			list = tcg.listMechanicsByTipoContrato(idTipoContrato);
			Checker.assertExistenMecanicosConTipoContrato(list);
			
			//Calculamos el acumulado por tipo contrato.
			for(Map<String,Object> m : list){
				acumulado += (Double) m.get("salario_bba");				
			}
			
			//Añadimos en un mapa el acumulado y el total de trabajadores.
			Map<String,Object> m = new HashMap<>();
			m.put("acumulado", acumulado);
			m.put("num_trabajadores", list.size());
			list.add(m);
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
	}
		
}
