package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.AddTipoContratoAction;
import uo.ri.amp.ui.admin.action.DeleteTipoContratoAction;
import uo.ri.amp.ui.admin.action.ListMechanicsByTipoContratoAction;
import uo.ri.amp.ui.admin.action.UpdateTipoContratoAction;
import alb.util.menu.BaseMenu;

/**
 * Menú para los casos de uso de la
 * Gestión de Tipos de Contrato
 * @author UO196582 Marcos Ruiz
 *
 */
public class TiposContratoMenu extends BaseMenu {

	public TiposContratoMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gestión de Tipos de Contrato", null },

				{ "Añadir tipo de contrato", AddTipoContratoAction.class },
				{ "Listar mecánicos por tipo de contrato activo",
					ListMechanicsByTipoContratoAction.class },
				{ "Borrar tipo de contrato", DeleteTipoContratoAction.class },
				{ "Modificar tipo de contrato",
					UpdateTipoContratoAction.class },
		};
	}

}
