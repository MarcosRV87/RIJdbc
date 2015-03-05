package uo.ri.amp.ui.admin.action;



import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Añadir un mecánico
 * en donde se realiza la interacción con el usuario.
 */
public class AddMechanicAction implements Action {

	@Override
	public void execute() {
		
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			adminservice.addMechanic(nombre, apellidos);
			Console.println("Nuevo mecánico añadido.");
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}		
	}

}
