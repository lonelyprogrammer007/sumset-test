package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPrbValoracionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.ICompromisoEstrategicoRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddPrbValoracionRepo;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad PddPrbValoracion
 * 
 * @author DANIEL
 * @version 1.0 20/03/2020
 */
@Service
public class PddPrbValoracionRepo extends OperacionesBasicasFacade<PddPrbValoracion, Long> implements IPddPrbValoracionServiceRepo, Serializable {

	/**
	 * Inyeccion de IPddPrbValoracionRepo
	 */
	@Autowired
	IPddPrbValoracionRepo pddPrbValoracionRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<PddPrbValoracion, Long> getRepo() {
		return pddPrbValoracionRepository;
	}
	
	@Override
	public PddPrbValoracion obtenerPorIdProblematicaYMomento(Long idProblematica, Long momento) throws SpddExceptions {
		return pddPrbValoracionRepository.obtenerPorIdProblematicaYMomento(idProblematica, momento);
	}
}
