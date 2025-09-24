package co.gov.sdp.spdd.data.dao.impl.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectoInversionRepo;
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ProyectoInversionRepo extends OperacionesBasicasFacade<ProyectoInversion, Long>
        implements IProyectoInversionServiceRepo, Serializable {

	/**
	 * 
	 */
    @Autowired
    IProyectoInversionRepo proyectoInversionRepository;

    /**
     * 
     */
    @Override
    public CrudRepository<ProyectoInversion, Long> getRepo() {
        return proyectoInversionRepository;
    }

	@Override
	public List<ProyectoInversion> proyectoInversionObtenerDisponibles() throws SpddExceptions {
		return proyectoInversionRepository.obtenerDisponibles();
	}
    
}
