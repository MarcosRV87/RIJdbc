package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.business.cash.impl.FacturarReparaciones;
import alb.util.console.Console;
import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();
		
		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while ( masAverias() );

		Map<String,Object> f = new FacturarReparaciones(idsAveria).execute();
		
		mostrarFactura(f);
	}

	private void mostrarFactura(Map<String, Object> f) {
		
		Console.printf("Factura nº: %d\n", f.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", f.get("fechaFactura"));
		Console.printf("\tTotal: %.2f €\n", f.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", f.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", f.get("totalConIva"));
	}


	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
