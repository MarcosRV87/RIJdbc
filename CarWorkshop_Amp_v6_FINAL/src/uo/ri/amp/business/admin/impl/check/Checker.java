package uo.ri.amp.business.admin.impl.check;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.common.BusinessException;

/*
 * Clase útil para hacer comprobaciones en la BBDD y ciertos cálculos 
 * de la lógica de negocio.
 */
public class Checker {

	public static void assertMecanicoExiste(Long idMecanico)
			throws BusinessException {
		if (idMecanico == 0) {
			throw new BusinessException(
					"Error: No se ha encontrado el mecánico.");
		}
	}

	public static void assertTipoContratoExiste(Long idTipoContrato)
			throws BusinessException {
		if (idTipoContrato == 0) {
			throw new BusinessException("Error: No existe el tipo de contrato.");
		}
	}

	public static void assertCategoriaExiste(Long idCategoria)
			throws BusinessException {
		if (idCategoria == 0) {
			throw new BusinessException(
					"Error: No existe la categoría de contrato.");
		}
	}

	public static void assertContratoExiste(Long idContrato)
			throws BusinessException {
		if (idContrato == 0) {
			throw new BusinessException("Error: No existe el contrato.");
		}
	}

	public static void assertNominaExiste(Long idNomina)
			throws BusinessException {
		if (idNomina == 0) {
			throw new BusinessException("Error: No existe la nómina.");
		}
	}

	public static void assertNoTieneNominasMecanico(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() > 0) {
			throw new BusinessException(
					"Error: El mecanico tiene nóminas asignadas.");
		}
	}

	public static void assertTieneNominasMecanico(List<Map<String, Object>> list)
			throws BusinessException {
		if (list.size() < 1) {
			throw new BusinessException(
					"Error: El mecanico no tiene nóminas asignadas.");
		}
	}

	public static void assertNoTieneContratosMecanico(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() > 0) {
			throw new BusinessException(
					"Error: El mecanico tiene contratos asignados.");
		}

	}
	
	public static void assertTieneContratosMecanico(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() < 1) {
			throw new BusinessException(
					"Error: El mecanico no tiene contratos asignados.");
		}

	}

	public static void assertNoTieneIntervencionesPorMecanico(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() > 0) {
			throw new BusinessException(
					"Error: El mecanico tiene intervenciones asignadas.");
		}

	}

	public static void assertNoTieneAveriasMecanico(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() > 0) {
			throw new BusinessException(
					"Error: El mecanico tiene averías asignadas.");
		}
	}
	
	public static void assertMecanicoTieneContratoActivo(Long idContrato) throws BusinessException {
		if (idContrato == (long) 0) {
			throw new BusinessException(
					"Error: El mecánico no tiene contrato activo.");
		}
	}
	
	

	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new RuntimeException("Error: No se ha podido hacer rollback.");
		}
	}

	public static void assertNoTieneAveriasDuranteContrato(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() > 0) {
			throw new BusinessException(
					"Error: El mecanico ha reparado averias durante el contrato.");
		}

	}

	public static void assertTipoContratoNoTieneContratos(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() > 0) {
			throw new BusinessException(
					"Error: Existen contratos con este tipo de contrato.");
		}
	}

	public static void assertExistenMecanicosConTipoContrato(
			List<Map<String, Object>> list) throws BusinessException {
		if (list.size() < 1) {
			throw new BusinessException(
					"Error: No existen mecanicos activos con este tipo de contrato.");
		}

	}

	public static void assertExistenNominas(List<Map<String, Object>> list)
			throws BusinessException {
		if (list.size() < 1) {
			throw new BusinessException(
					"Error: No existen nóminas registradas.");
		}
	}
	
	public static void assertContratoMasDeUnAnho(
			List<Map<String, Object>> nominas) throws BusinessException {
		if (nominas.size() < 12) {
			throw new BusinessException(
					"Error: Duración del contrato insuficiente.");
		}
		
	}

	public static Map<String, Object> calcularSalariosNomina(
			Map<String, Object> nomina) {
		Map<String, Object> nominaFinal = new HashMap<>();
		double bruto = 0.0;
		double neto = 0.0;
		double descuento_irpf = 0.0;
		double descuentoss = 0.0;

		// Calculamos el bruto
		bruto = (Double) nomina.get("salario_base")
				+ (Double) nomina.get("pago_extra")
				+ (Double) nomina.get("importe_plus")
				+ (Double) nomina.get("importe_trienios");

		nominaFinal.put("bruto", bruto);

		// Calculamos el IRPF
		descuento_irpf = (bruto * (Double) nomina.get("descuento_irpf")) / 100;

		// Calculamos la SS
		descuentoss = ((Double) nomina.get("descuentoss") * ((Double) nomina
				.get("salario_base") + (Double) nomina.get("pago_extra"))) / 100;

		// Calculamos el neto
		neto = bruto - (descuento_irpf + descuentoss);

		nominaFinal.put("neto", neto);
		nominaFinal.put("descuento_irpf", descuento_irpf);
		nominaFinal.put("descuentoss", descuentoss);
		nominaFinal.put("fecha", nomina.get("fecha"));
		nominaFinal.put("id", nomina.get("id"));

		return nominaFinal;
	}

	

	

}
