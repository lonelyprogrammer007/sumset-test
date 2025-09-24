package co.gov.sdp.spdd.data.dao.impl.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IEntidadRepo;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class EntidadRepo extends OperacionesBasicasFacade<Entidad, String> implements IEntidadServiceRepo, Serializable {

	/**
	 * 
	 */
    @Autowired
    IEntidadRepo entidadRepository;

    /**
     * 
     */
    @Override
    public CrudRepository<Entidad, String> getRepo() {
        return entidadRepository;
    }

}
