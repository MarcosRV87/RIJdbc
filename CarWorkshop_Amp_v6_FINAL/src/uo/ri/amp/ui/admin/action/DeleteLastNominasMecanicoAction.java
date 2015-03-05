package uo.ri.amp.ui.admin.action;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;
/*
 * Clase que modela la capa de presentación del caso de uso
 * Eliminar la última nómina generada a un contrato de un mecánico
 * en donde se realiza la interacción con el usuario.
 */
public class DeleteLastNominasMecanicoAction implements Action {

	@Override
	public void execute() {

		Long idMecanico = Console.readLong("Id del mecánico");
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			adminservice.deleteLastNominaMecanico(idMecanico);
			Console.print("Ultima nómina del mecánico eliminada.");
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
		
		
	}

}
