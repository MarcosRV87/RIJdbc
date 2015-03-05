package uo.ri.ui.admin.action;

import uo.ri.common.BusinessException;
import uo.ri.business.admin.AdminServices;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		Long id = Console.readLong("Id del mecánico"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		//Procesar
		AdminServices adminservice = ServicesFactory.createAdminServices();
		adminservice.updateMechanic(id, nombre, apellidos);
		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
