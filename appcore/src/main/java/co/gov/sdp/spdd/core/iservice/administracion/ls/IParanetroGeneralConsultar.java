package co.gov.sdp.spdd.core.iservice.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja todos los metodos asociados con una consulta
 *
 * @author Bryan Munoz
 *
 */
public interface IParanetroGeneralConsultar {

    /**
     * Metodo que obtiene una lista parametro general
     *
     * @return un objeto tipo generico dto con la lista, un codigo y mensaje de
     * respuesta
     */
    public GenericoDTO obtenerTodos() throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     */
	public GenericoDTO obtenerPaginado(ParametroGeneralDTO peticion) throws SpddExceptions;

}
