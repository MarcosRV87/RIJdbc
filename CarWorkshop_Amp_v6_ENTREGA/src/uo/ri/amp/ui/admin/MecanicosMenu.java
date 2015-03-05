package uo.ri.amp.ui.admin;


import uo.ri.amp.ui.admin.action.AddMechanicAction;
import uo.ri.amp.ui.admin.action.DeleteMechanicAction;
import uo.ri.amp.ui.admin.action.ListActiveMechanicsAction;
import uo.ri.amp.ui.admin.action.ListMechanicsAction;
import uo.ri.amp.ui.admin.action.UpdateMechanicAction;
import alb.util.menu.BaseMenu;

/**
 * Menú para los casos de uso de la
 * Gestión de Mecánicos
 * @author UO196582 Marcos Ruiz
 *
 */
public class MecanicosMenu extends BaseMenu {

	public MecanicosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de mecánicos", null},
			
			{ "Añadir mecánico", 				AddMechanicAction.class }, 
			{ "Modificar datos de mecánico", 	UpdateMechanicAction.class }, 
			{ "Eliminar mecánico", 				DeleteMechanicAction.class }, 
			{ "Listar mecánicos activos", 				ListActiveMechanicsAction.class },
			{ "Listar mecánicos registrados", 				ListMechanicsAction.class },
		};
	}

}
