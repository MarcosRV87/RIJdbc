package uo.ri.amp.ui.admin.action;

import java.util.HashMap;
import java.util.Map;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Ver nómina en detalle
 * en donde se realiza la interacción con el usuario.
 */
public class NominaInDetailAction implements Action {

	@Override
	public void execute() {

		Long idNomina = Console.readLong("ID de la nómina");
		Map<String, Object> nomina = new HashMap<>();

		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			nomina = adminservice.nominaInDetail(idNomina);
			Console.printf("\tBruto: %s \tNeto: %s \tIRPF: %s \tSS: %s\n",
					nomina.get("bruto"),
					nomina.get("neto"),
					nomina.get("descuento_irpf"),
					nomina.get("descuentoss")
					);
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}

	}

}
