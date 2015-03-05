package uo.ri.business.admin.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.MecanicosGatewayImpl;
import alb.util.jdbc.Jdbc;

public class ListMechanics {

	
	
	public List<Map<String, Object>> execute() { 

		Connection c = null;
		MecanicosGateway mg = new MecanicosGatewayImpl();
		List<Map<String, Object>> res = new ArrayList<>();

		try {
			c = Jdbc.getConnection();
			mg.setConnection(c);
			res = mg.findAll();
			
			return res;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
