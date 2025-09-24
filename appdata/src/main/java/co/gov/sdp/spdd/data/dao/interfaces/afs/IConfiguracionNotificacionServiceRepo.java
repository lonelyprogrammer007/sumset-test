package co.gov.sdp.spdd.data.dao.interfaces.afs;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IConfiguracionNotificacionServiceRepo extends IOperacionesBasicasFacade<ConfiguracionNotificacion, Long> {

	/**
	 * 
	 * @param componente
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	FiltroDTO filtrarPorCampo(ConfiguracionNotificacionDTO componente, Long inicio, Integer limite)
			throws SpddExceptions;

}
