package uo.ri.amp.conf;

import uo.ri.amp.persistence.AveriasGateway;
import uo.ri.amp.persistence.CategoriasGateway;
import uo.ri.amp.persistence.ContratosGateway;
import uo.ri.amp.persistence.IntervencionesGateway;
import uo.ri.amp.persistence.MecanicosGateway;
import uo.ri.amp.persistence.NominasGateway;
import uo.ri.amp.persistence.TiposContratoGateway;
import uo.ri.amp.persistence.impl.AveriasGatewayImpl;
import uo.ri.amp.persistence.impl.CategoriasGatewayImpl;
import uo.ri.amp.persistence.impl.ContratosGatewayImpl;
import uo.ri.amp.persistence.impl.IntervencionesGatewayImpl;
import uo.ri.amp.persistence.impl.MecanicosGatewayImpl;
import uo.ri.amp.persistence.impl.NominasGatewayImpl;
import uo.ri.amp.persistence.impl.TiposContratoGatewayImpl;

/*
 * Clase en la que se proporcionan los diferentes
 * tipos de servicios que necesita la aplicación
 * dependiendo de la tabla que estemos manejando mediante
 * una factoría para crear cada uno de los
 * Table Data Gateway (TDG).
 *
 */

public class PersistenceFactory {

	public static MecanicosGateway getMecanicoGateway(){
		return new MecanicosGatewayImpl();
	}
	
	
	public static AveriasGateway getAveriasGateway(){
		return new AveriasGatewayImpl();
	}
	
	
	public static IntervencionesGateway getIntervencionesGateway(){
		return new IntervencionesGatewayImpl();
	}

	public static ContratosGateway getContratosGateway() {
		return new ContratosGatewayImpl();
	}

	public static NominasGateway getNominasGateway() {
		return new NominasGatewayImpl();
	}

	public static TiposContratoGateway getTiposContratoGateway() {
		return new TiposContratoGatewayImpl();
	}

	public static CategoriasGateway getCategoriasGateway() {
		return new CategoriasGatewayImpl();
	}
	
}
