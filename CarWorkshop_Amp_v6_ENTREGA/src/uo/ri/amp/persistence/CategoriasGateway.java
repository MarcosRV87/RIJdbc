package uo.ri.amp.persistence;

import java.sql.Connection;

/*
 * Interfaz referente al Table Data Gateway de
 * Categorías
 * mediante la que se accede a la base de datos
 * para realizar las consultas.
 */
public interface CategoriasGateway {
	
	void setConnection(Connection con);
	Long existeCategoria(Long id);

}
