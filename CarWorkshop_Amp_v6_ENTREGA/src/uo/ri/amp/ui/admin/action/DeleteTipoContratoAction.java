package uo.ri.amp.ui.admin.action;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Eliminar tipo de contrato
 * en donde se realiza la interacción con el usuario.
 */
public class DeleteTipoContratoAction implements Action{

	@Override
	public void execute() {
		
		Long idTipoContrato = Console.readLong("Id del Tipo de Contrato");
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			adminservice.deleteTipoContrato(idTipoContrato);
			Console.println("Se ha eliminado el tipo de contrato.");
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
		
	}

}
