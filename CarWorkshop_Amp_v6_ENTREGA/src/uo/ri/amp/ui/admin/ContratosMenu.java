package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.AddContractAction;
import uo.ri.amp.ui.admin.action.DeleteContractAction;
import uo.ri.amp.ui.admin.action.FinishContractMechanicAction;
import uo.ri.amp.ui.admin.action.ListContractMechanicAction;
import uo.ri.amp.ui.admin.action.UpdateContractAction;
import alb.util.menu.BaseMenu;

/**
 * Menú para los casos de uso de la
 * Gestión de Contratos
 * @author UO196582 Marcos Ruiz
 *
 */
public class ContratosMenu extends BaseMenu {
	
	public ContratosMenu(){
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de contratos", null},
				
				{ "Añadir contrato a mecánico", 				AddContractAction.class }, 
				{ "Eliminar contrato", 				DeleteContractAction.class },
				{ "Modificar contrato", 				UpdateContractAction.class },
				{ "Listar contratos por trabajador", 				ListContractMechanicAction.class },
				{ "Finalizar contrato", 				FinishContractMechanicAction.class },
			};
	}

}
