package uo.ri.amp.ui.admin.action;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Eliminar Contrato a un mecánico
 * en donde se realiza la interacción con el usuario.
 */
public class DeleteContractAction implements Action {

	@Override
	public void execute() {
		Long idContrato = Console.readLong("Id del contrato");

		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			adminservice.deleteContratoMechanic(idContrato);
			Console.println("Se ha eliminado el contrato.");
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}

	}

}
