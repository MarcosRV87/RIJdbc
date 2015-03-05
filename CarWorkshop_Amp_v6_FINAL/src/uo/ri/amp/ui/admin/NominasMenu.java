package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.DeleteLastNominasMecanicoAction;
import uo.ri.amp.ui.admin.action.ListAllNominasAction;
import uo.ri.amp.ui.admin.action.ListNominasMecanicoAction;
import uo.ri.amp.ui.admin.action.NominaInDetailAction;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

/**
 * Menú para los casos de uso de la
 * Gestión de Nóminas
 * @author UO196582 Marcos Ruiz
 *
 */
public class NominasMenu extends BaseMenu {
	
	public NominasMenu(){
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de nóminas", null},
				
				{ "Listar nóminas de empleado", 				ListNominasMecanicoAction.class }, 
				{ "Listar todas las nóminas", 				ListAllNominasAction.class },
				{ "Ver nómina en detalle", 				NominaInDetailAction.class },
				{ "Eliminar última nómina generada de mecánico", 				DeleteLastNominasMecanicoAction.class },
				{ "Generación automática de nóminas mensual", 				NotYetImplementedAction.class },
			};
	}
}
