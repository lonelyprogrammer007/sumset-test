package co.gov.sdp.spdd.core.iservice.presupuesto;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
public interface IInformacionPresupuestalPresupuestoRegistrar {

    /**
     * Metodo para crear informacion presupuestal
     *
     * @param peticion Objeto DTO con los datos para crear la informacion
     * presupuestal
     * @return Objeto DTO informando la creacion exitosa de la informacion
     * presupuestal, sino un objeto vacio con el mensaje de error
     * correspondiente
     */
    public InformacionPresupuestalDTO crearInformacionPresupuestal(InformacionPresupuestalDTO peticion) throws SpddExceptions, JsonProcessingException;
}
