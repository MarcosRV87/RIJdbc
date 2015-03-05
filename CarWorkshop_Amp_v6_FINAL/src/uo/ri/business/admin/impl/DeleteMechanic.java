package uo.ri.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.MecanicosGatewayImpl;
import alb.util.jdbc.Jdbc;

public class DeleteMechanic{

	private Long idMecanico;

	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() throws BusinessException {
		
		Connection c = null;
		MecanicosGateway mg = new MecanicosGatewayImpl();

		try {
			c = Jdbc.getConnection();
			mg.setConnection(c);
			mg.delete(idMecanico);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}

	}

}
