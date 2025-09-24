package co.gov.sdp.spdd.core.service.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;

/**
 * Clase que tiene los metodos de eliminar relacionado con archivoProcesado
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ArchivoProcesadoCargaEliminar implements IArchivoProcesadoCargaEliminar {

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

}
