package co.gov.sdp.spdd.data.dao.impl.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectosInversionUsuarioRepo;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * @author SumSet 2019
 *
 */
@Transactional
@Service
public class ProyectosInversionUsuarioRepo extends OperacionesBasicasFacade<ProyectosInversionUsuario, Long>
        implements IProyectosInversionUsuarioServiceRepo, Serializable {

	/**
	 * 
	 */
    @Autowired
    IProyectosInversionUsuarioRepo proyectosInversionUsuarioRepository;

    /**
     * 
     */
    @Override
    public CrudRepository<ProyectosInversionUsuario, Long> getRepo() {
        return proyectosInversionUsuarioRepository;
    }
    
    /**
     * 
     */
    @Override
    public List<ProyectosInversionUsuario> obtenerPorUsuario(String usuario){
		return proyectosInversionUsuarioRepository.obtenerPorUsuario(usuario);
    	
    }

}
