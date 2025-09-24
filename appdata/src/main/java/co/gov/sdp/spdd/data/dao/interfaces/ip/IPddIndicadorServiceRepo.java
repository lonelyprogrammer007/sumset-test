package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddIndicadorServiceRepo extends IOperacionesBasicasFacade<PddIndicador, Long> {

	/**
	 * Metodo que valida que ya exista un indicador por los campos que se piden en
	 * los parametros
	 * 
	 * @param denominacion  nombre del indicador
	 * @param magnitud      campo a validar para obtener indicador
	 * @param periodicidad  campo a validar para obtener indicador
	 * @param fuenteExterna campo a validar para obtener indicador
	 * @param fuente        campo a validar para obtener indicador
	 * @param lineaBase     campo a validar para obtener indicador
	 * @param magnitudLb    campo a validar para obtener indicador
	 * @param lineaBaseDesc campo a validar para obtener indicador
	 * @return si existe o no un indicador
	 * @throws SpddExceptions
	 */
	public List<PddIndicador> obtenerPddIndicadorMetaResultado(String denominacion, Long magnitud, String periodicidad,
			Long fuenteExterna, String fuente, Long lineaBase, Long magnitudLb, String lineaBaseDesc)
			throws SpddExceptions;
}
