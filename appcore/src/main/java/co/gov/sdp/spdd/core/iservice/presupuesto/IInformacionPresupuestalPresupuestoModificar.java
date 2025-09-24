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
public interface IInformacionPresupuestalPresupuestoModificar {

    /**
     * Metodo para modificar informacion presupuestal
     *
     * @param peticion Objeto DTO con los datos para modificar la informacion
     * presupuestal
     * @return Objeto DTO informando la modificacion exitosa de la informacion
     * presupuestal, sino un objeto vacio con el mensaje de error
     * correspondiente
     */
    public InformacionPresupuestalDTO modificarInformacionPresupuestal(InformacionPresupuestalDTO peticion) throws SpddExceptions;
}
