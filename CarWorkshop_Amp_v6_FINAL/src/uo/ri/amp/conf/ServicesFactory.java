package uo.ri.amp.conf;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.business.admin.impl.AdminServicesImpl;

/*
 * Clase en la que se proporcionan los diferentes
 * tipos de servicios que necesita la aplicación
 * dependiendo del tipo de usuario. Se utiliza una
 * factoría para generar cada uno de ellos.
 *
 */
public class ServicesFactory {

	public static AdminServices createAdminServices() {
		return new AdminServicesImpl();
	}

}
