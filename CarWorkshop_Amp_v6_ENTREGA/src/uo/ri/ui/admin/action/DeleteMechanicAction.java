package uo.ri.ui.admin.action;

import uo.ri.common.BusinessException;
import uo.ri.business.admin.AdminServices;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
	//Procesar
		AdminServices adminservice = ServicesFactory.createAdminServices();
		adminservice.deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
