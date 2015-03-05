package uo.ri.conf;

import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.SustitucionesGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;
import uo.ri.persistence.impl.SustitucionesGatewayImpl;

public class PersistenceFactory {

	public static MecanicosGateway getMecanicoGateway(){
		return new MecanicosGatewayImpl();
	}
	
	public static SustitucionesGateway getSustitucionesGateway(){
		return new SustitucionesGatewayImpl();
	}
	
	public static AveriasGateway getAveriasGateway(){
		return new AveriasGatewayImpl();
	}
	
	public static FacturasGateway getFacturasGateway(){
		return new FacturasGatewayImpl();
	}
	
}
