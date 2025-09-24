package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de registro para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEstructuraMetaAdministracionRegistrar {

    /**
     * Metodo para crear estructura meta
     *
     * @param peticion Objeto DTO con los datos para crear la estructura meta
     * @return Objeto DTO informando la creacion exitosa del componente de
     * gasto, sino un objeto vacio con el mensaje de error correspondiente
     */
    public EstructuraMetaDTO crearEstructuraMeta(EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException;

}
