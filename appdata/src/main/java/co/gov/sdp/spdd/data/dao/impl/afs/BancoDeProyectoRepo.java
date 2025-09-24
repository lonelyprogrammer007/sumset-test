package co.gov.sdp.spdd.data.dao.impl.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.afs.IBancoDeProyectoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IBancoDeProyectoRepo;
import co.gov.sdp.spdd.data.model.afs.BancoDeProyectos;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class BancoDeProyectoRepo extends OperacionesBasicasFacade<BancoDeProyectos, Long>
        implements IBancoDeProyectoServiceRepo, Serializable {

	/**
	 * 
	 */
    @Autowired
    IBancoDeProyectoRepo bancoProyectoRepository;

    /**
     * 
     */
    @Override
    public CrudRepository<BancoDeProyectos, Long> getRepo() {
        return bancoProyectoRepository;
    }

}
