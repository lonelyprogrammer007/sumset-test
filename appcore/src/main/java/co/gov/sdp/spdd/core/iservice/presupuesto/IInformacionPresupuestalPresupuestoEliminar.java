package co.gov.sdp.spdd.core.iservice.presupuesto;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
public interface IInformacionPresupuestalPresupuestoEliminar {
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar una informacion presupuestal
	 * @param idInformacionPresupuestal Long que representa el identificador de la informacion presupuestal a eliminar
	 * @return un objeto de tipo InformacionPresupuestalDTO con la informacion eliminada
	 * @throws SpddExceptions
	 */
	public InformacionPresupuestalDTO eliminarInformacionPresupuestal(Long idInformacionPresupuestal)
			throws SpddExceptions;

}
