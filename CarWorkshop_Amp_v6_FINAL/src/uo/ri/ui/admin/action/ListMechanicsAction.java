package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.business.admin.AdminServices;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {
	
	@Override
	public void execute() throws BusinessException {

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
