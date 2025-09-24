package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos asociados con consultar un
 * potProyectoInstrumento
 *
 * @author Bryan Munoz
 *
 */
public interface IPotProyectoInstrumentoConsultar {

    /**
     * Metodo que obtiene todos los potProyectoInstrumento
     *
     * @return una lista con todos los potProyectoInstrumentos en la bd
     */
    public GenericoDTO obtenerTodosProyectoInstrumento() throws SpddExceptions;
    
    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     */
	public GenericoDTO obtenerPaginado(PotProyectoInstrumentoDTO peticion) throws SpddExceptions;
}
