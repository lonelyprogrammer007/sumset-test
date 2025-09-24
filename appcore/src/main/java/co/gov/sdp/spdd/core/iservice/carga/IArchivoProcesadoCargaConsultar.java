package co.gov.sdp.spdd.core.iservice.carga;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IArchivoProcesadoCargaConsultar {

    /**
     * Metodo para obtener la lista de todos las informacion presupuestal
     *
     * @return Objeto Generico que contiene la lista de todas las informacion
     * presupuestal
     */
    public GenericoDTO obtenerArchivosProcesadosTodos() throws SpddExceptions;
    
    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     */
    public GenericoDTO obtenerPaginado(ArchivoProcesadoDTO peticion) throws SpddExceptions;
}
