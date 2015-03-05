package uo.ri.ui.admin.action;


import uo.ri.business.admin.AdminServices;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AddMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Procesar
		AdminServices adminservice = ServicesFactory.createAdminServices();
		adminservice.addMechanic(nombre, apellidos);
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
