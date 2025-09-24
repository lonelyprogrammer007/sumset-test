package co.gov.sdp.spdd.core.service.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;

/**
 * Esta clase implementa todos los metodos de modificar de archivoProcesado
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ArchivoProcesadoCargaModificar implements IArchivoProcesadoCargaModificar {

    /**
     * Objeto que me trae consultar especificas de la entidad
     */
    @Autowired
    IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

    /**
     * Objeto que permite el mapeo de componente de gestion
     */
    @Autowired
    ArchivoProcesadoMapper archivoProcesadoMapper;

    /**
     * 
     */
    @Override
    public ArchivoProcesadoDTO modificarArchivoProcesado(ArchivoProcesadoDTO peticion) throws SpddExceptions {
        ArchivoProcesado archivoProcesado = archivoProcesadoServiceRepo.obtener(peticion.getIdArchivo());
        ArchivoProcesadoDTO respuesta = new ArchivoProcesadoDTO();
        if (archivoProcesado == null || archivoProcesado.getIdArchivo() == null) {
            respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
            respuesta.setMsgRespuesta(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_COMPONENTE_GASTO_NO_ENCONTRADO,
                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
            return respuesta;
        }
        archivoProcesado = archivoProcesadoMapper.ArchivoProcesadoDTOToEntity(peticion);
        archivoProcesadoServiceRepo.guardar(archivoProcesado);
        respuesta = archivoProcesadoMapper.ArchivoProcesadoEntityToDTO(archivoProcesado);
        respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
        respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGAR_ARCHIVO_PLANO_CORRECTO,
                PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
        return respuesta;
    }

}
