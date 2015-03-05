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
import uo.ri.amp.persistence.ContratosGateway;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.NominasGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Mostrar los contratos de un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class ListContractMechanic {

	private Long idMecanico;
	Connection con;

	public ListContractMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public List<Map<String, Object>> execute() throws BusinessException {

		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		NominasGateway ng = PersistenceFactory.getNominasGateway();
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String,Object> activo = new HashMap<>();
		
		try {
			con = Jdbc.getConnection();
			cg.setConnection(con);
			mg.setConnection(con);
			ng.setConnection(con);

			Long encontrado = mg.existeMecanico(idMecanico);
			Checker.assertMecanicoExiste(encontrado);
			list = cg.verificarContratosMecanico(idMecanico);
			Checker.assertTieneContratosMecanico(list);
			
			list = cg.getContratosExtintosMecanico(idMecanico);
			
			activo = cg.getContratoActivoMecanico(idMecanico);
			
			list.add(activo);
			
			for(Map<String,Object> m : list){
				int num_nominas;
				num_nominas = ng.getNumNominasByContrato((Long) m.get("id"));
				m.put("num_nominas", num_nominas);
			}
			
			return list;			

		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
	}

}
