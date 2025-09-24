package co.gov.sdp.spdd.core.iservice.carga;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
public interface IArchivoProcesadoCargaModificar {

    /**
     * Metodo para modificar los archivos procesados
     *
     * @param peticion Objeto DTO con los datos para modificar los archivos
     * procesados
     * @return Objeto DTO informando la modificacion exitosa de la informacion
     * presupuestal, sino un objeto vacio con el mensaje de error
     * correspondiente
     */
    public ArchivoProcesadoDTO modificarArchivoProcesado(ArchivoProcesadoDTO peticion) throws SpddExceptions;
}
