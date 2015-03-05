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
 * Modificar Contrato a un mecánico
 * en donde se realiza la interacción con el usuario.
 */
public class UpdateContractAction implements Action {

	@Override
	public void execute() {
		Long idContrato = Console.readLong("Id del contrato"); 
		
		Date fechaCont = DateUtil.fromString(Console
			    .readString("fecha de contratación (dd-mm-aaaa)"));
		Date fechaExt = DateUtil.fromString(Console
			    .readString("fecha de extinción (dd-mm-aaaa)"));

		Double salarioBBA = Console.readDouble("Salario base bruto anual");
		Long tipo_contrato_id = Console.readLong("ID Tipo contrato");
		Long categoria_id = Console.readLong("ID Categoria contrato");
		
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			adminservice.updateContratoMecanico(idContrato, fechaCont, fechaExt, salarioBBA, tipo_contrato_id, categoria_id);
			Console.println("Contrato actualizado.");
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
	}

}
