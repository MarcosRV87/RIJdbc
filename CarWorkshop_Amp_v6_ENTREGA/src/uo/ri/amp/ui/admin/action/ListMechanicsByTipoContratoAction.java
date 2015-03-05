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
 * Listar todos los mecánicos activos dependiendo del tipo de contrato
 * en donde se realiza la interacción con el usuario.
 */
public class ListMechanicsByTipoContratoAction implements Action {

	@Override
	public void execute() {
		
		Long idTipoContrato = Console.readLong("ID Tipo Contrato");
		List<Map<String, Object>> list = new ArrayList<>();
		AdminServices adminservice = ServicesFactory.createAdminServices();
		
		try {
			list = adminservice.listMechanicsByTipoContrato(idTipoContrato);
			Map<String,Object> m = new HashMap<>();
			for(int i = 0; i<list.size()-1; i++){
				m = list.get(i);
				Console.printf("\t%s %s %s %s\n",  
						m.get("id")	,
						m.get("apellidos")	,
						m.get("nombre")	,
						m.get("salario_bba")
						);
			}
			
			m = list.get(list.size()-1);
			Console.printf("\t\tAcumulado: %s€ \tNúmero mecánicos: %d\n", m.get("acumulado"), m.get("num_trabajadores"));
		}  catch (BusinessException e) {
			Printer.printBusinessException(e);
		} catch (RuntimeException re) {
			Printer.printRuntimeException(re);
		}
		
		
		
	}

}
