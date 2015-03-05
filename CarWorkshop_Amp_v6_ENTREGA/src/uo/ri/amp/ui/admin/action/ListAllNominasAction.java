package uo.ri.amp.ui.admin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Listar todas las nóminas
 * en donde se realiza la interacción con el usuario.
 */
public class ListAllNominasAction implements Action {

	@Override
	public void execute() {
		Console.println("\nListado de nóminas\n");
		List<Map<String, Object>> res = new ArrayList<>();
		AdminServices adminservice = ServicesFactory.createAdminServices();
		try {
			res = adminservice.listAllNominas();
			for (Map<String, Object> m : res) {
				Console.printf("\tID: %s \tFecha: %s \tNeto: %s\n",
						m.get("id"),
						m.get("fecha"),
						m.get("neto")
						);
			}
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}

	}

}
