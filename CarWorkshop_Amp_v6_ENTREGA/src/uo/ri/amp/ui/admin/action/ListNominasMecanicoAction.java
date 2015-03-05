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
 * Listar las nóminas de un mecánico
 * en donde se realiza la interacción con el usuario.
 */
public class ListNominasMecanicoAction implements Action {

	@Override
	public void execute() {
		
		Long idMecanico = Console.readLong("Id del mecánico: ");
		
		AdminServices adminservice = ServicesFactory.createAdminServices();
		List<Map<String, Object>> res = new ArrayList<>();
		
		try {
			res = adminservice.listNominasByMechanic(idMecanico);
		} catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
		
		Console.println("\nListado de nóminas\n");  
		for(Map<String,Object> m : res){
			Console.printf("\t%d %s %s %s\n",  
					m.get("id")	,
					m.get("salario_base")	,
					m.get("contrato_id")	,
					m.get("fecha")
					);
		}
		
	}
	
	

}
