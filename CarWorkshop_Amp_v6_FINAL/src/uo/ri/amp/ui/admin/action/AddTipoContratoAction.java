package uo.ri.amp.ui.admin.action;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Añadir tipo de contrato 
 * en donde se realiza la interacción con el usuario.
 */
public class AddTipoContratoAction implements Action {

	@Override
	public void execute() {

		String nombre = Console.readString("Nombre del tipo de contrato");
		int num_dias = Console.readInt("Días de indemnización");

		AdminServices adminservice = ServicesFactory.createAdminServices();
		
		try {
			adminservice.addTipoContrato(nombre, num_dias);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
	}

}
