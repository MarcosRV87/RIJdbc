package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.CategoriasGateway;

/*
 * Implementación referente al Table Data Gateway de
 * Categorías
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public class CategoriasGatewayImpl implements CategoriasGateway {


	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	@Override
	public Long existeCategoria(Long id) {

		try {
			pst = con.prepareStatement(Conf.get("SQL_CATEGORIA_EXISTE"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1);
			}
			return (long) 0;

		} catch (SQLException e) {
			throw new RuntimeException("Error SQL buscando categoría de contrato.");
		}

	}
}
