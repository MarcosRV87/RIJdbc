package uo.ri.amp.ui.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
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
 * Listar contratos de mecánico
 * en donde se realiza la interacción con el usuario.
 */
public class ListContractMechanicAction implements Action {

	@Override
	public void execute(){

		Long idMecanico = Console.readLong("ID del mecánico");
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> m = new HashMap<>();
		AdminServices adminservice = ServicesFactory.createAdminServices();
		
		try {
			list = adminservice.listContractMechanic(idMecanico);
			Console.println("\tID: \tNominas Imp. Ext. \tStatus");
			for (int i = 0; i < list.size() - 1; i++) {
				m = list.get(i);
				Console.printf("\t%s \t%s \t%s \t\t%s\n", m.get("id"),
						m.get("num_nominas"), m.get("importe_extincion"),
						m.get("status"));
			}

			m = list.get(list.size() - 1);
			Console.printf("\t%s \t%s \t\t\t%s\n", m.get("id"), m.get("num_nominas"),
					m.get("status"));
		}  catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
	}

}
