package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEstructuraMetaAdministracionModificar {

    

    /**
     * Metodo para modificar estructura meta
     *
     * @param peticion Objeto DTO con los datos para modificar la estructura
     * meta
     * @return Objeto DTO informando la modificacion exitosa de la estructura
     * meta, sino un objeto vacio con el mensaje de error correspondiente
     */
    public EstructuraMetaDTO modificarEstructuraMeta(EstructuraMetaDTO peticion) throws SpddExceptions;
}
