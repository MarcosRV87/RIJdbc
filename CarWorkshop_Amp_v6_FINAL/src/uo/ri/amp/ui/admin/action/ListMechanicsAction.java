package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.business.admin.AdminServices;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/*
 * Clase que modela la capa de presentación del caso de uso
 * Listar todos los mecánicos
 * en donde se realiza la interacción con el usuario.
 */
public class ListMechanicsAction implements Action {
	
	@Override
	public void execute() {

		Console.println("\nListado de mecánicos\n");  
		AdminServices adminservice = ServicesFactory.createAdminServices();
		List<Map<String, Object>> res = adminservice.listMechanics();
		
		for(Map<String,Object> m : res){
			Console.printf("\t%d %s %s\n",  
					m.get("id")	,
					m.get("nombre")	,
					m.get("apellidos")
					);
		}
	
	}
}
