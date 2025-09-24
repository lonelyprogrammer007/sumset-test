package co.gov.sdp.spdd.data.dao.impl.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGestionUsuarioRepo;
import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ComponenteGestionUsuarioRepo extends OperacionesBasicasFacade<ComponenteGestionUsuario, Long>
        implements IComponenteGestionUsuarioServiceRepo, Serializable {

	/**
	 * 
	 */
    @Autowired
    IComponenteGestionUsuarioRepo componenteGestionUsuarioRepository;

    /**
     * 
     */
    @Override
    public CrudRepository<ComponenteGestionUsuario, Long> getRepo() {
        return componenteGestionUsuarioRepository;
    }

}
