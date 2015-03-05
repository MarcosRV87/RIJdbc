package uo.ri.amp.ui.admin.action;

import java.util.Date;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Modificar tipo de contrato
 * en donde se realiza la interacción con el usuario.
 */
public class UpdateTipoContratoAction implements Action{

	@Override
	public void execute() {
		
		Long idTipoContrato = Console.readLong("Id del tipo de contrato");
		String nombre = Console.readString("Nombre del contrato");
		int num_dias = Console.readInt("Numero de dias indemnizacion");
		
		Date fecha_fin = DateUtil.fromString(Console
			    .readString("fecha de fin de contrato (dd-mm-aaaa)"));
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			adminservice.updateTipoContrato(idTipoContrato, nombre, num_dias, fecha_fin);
			Console.println("Tipo de contrato modificado.");
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
		
	}

}
