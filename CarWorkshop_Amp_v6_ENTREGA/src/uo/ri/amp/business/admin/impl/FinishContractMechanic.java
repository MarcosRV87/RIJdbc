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
import uo.ri.amp.persistence.TiposContratoGateway;
import alb.util.jdbc.Jdbc;

/*
 * Clase que modela la capa de lógica de negocio para
 * el Transaction Script para el caso de uso de
 * Extinguir contrato a un mecánico
 * en donde se realizan los cálculos necesarios para
 * acceder a la base de datos y
 * devolver a la capa de presentación.
 */
public class FinishContractMechanic {

	private Long idMecanico;

	Connection con;

	public FinishContractMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() throws BusinessException {

		ContratosGateway cg = PersistenceFactory.getContratosGateway();
		MecanicosGateway mg = PersistenceFactory.getMecanicoGateway();
		NominasGateway ng = PersistenceFactory.getNominasGateway();
		TiposContratoGateway tcg = PersistenceFactory.getTiposContratoGateway();

		Map<String, Object> m = new HashMap<>();
		List<Map<String, Object>> nominas = new ArrayList<>();
		double extincion = 0.0;

		try {
			con = Jdbc.getConnection();
			con.setAutoCommit(false);
			cg.setConnection(con);
			mg.setConnection(con);
			ng.setConnection(con);
			tcg.setConnection(con);

			Long encontrado = mg.existeMecanico(idMecanico);
			Checker.assertMecanicoExiste(encontrado);

			m = cg.getContratoActivoMecanico(encontrado);
			Long idContrato = ((Long) m.get("id"));
			Checker.assertMecanicoTieneContratoActivo(idContrato);
			Checker.assertContratoExiste(idContrato);
			
			int num_dias = tcg.getNumDiasContrato(idContrato);

			nominas = ng.getNominasByContrato(idContrato);
			Checker.assertContratoMasDeUnAnho(nominas);

			//Calculamos el bruto total anual
			for (int i = 0; i < 12; i++) {
				m = nominas.get(i);
				extincion += (Double) m.get("salario_base")
						+ (Double) m.get("pago_extra")
						+ (Double) m.get("importe_plus")
						+ (Double) m.get("importe_trienios");
			}
			
			extincion = ((extincion * num_dias) / 365 );
			
			//Poner importe_extincion
			cg.updateImporteExtincion(idContrato,extincion);
			
			//Poner contrato a extinto
			cg.updateContratoaExtinto(idContrato);
			
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Error estableciendo conexión con la base de datos.");
		} finally {
			Jdbc.close(con);
		}
	}

}
