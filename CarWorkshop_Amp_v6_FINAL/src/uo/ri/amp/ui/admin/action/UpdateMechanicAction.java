package uo.ri.amp.ui.admin.action;


/*
 * Clase que modela la capa de presentación del caso de uso
 * Modificar mecánico
 * en donde se realiza la interacción con el usuario.
 */
import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() {
		
		Long id = Console.readLong("Id del mecánico");
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
	
		try {
			adminservice.updateMechanic(id, nombre, apellidos);
			Console.println("Mecánico actualizado.");
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		}
		
	}

}
