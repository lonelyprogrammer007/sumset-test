package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddCompetenciaAsociadaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddCompetenciaAsociadaRepo;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository.
 * @author DANIEL
 * @version 1.0 19/03/2020
 */
@Service
public class PddCompetenciaAsociadaRepo extends OperacionesBasicasFacade<PddCompetenciaAsociada, Long> implements IPddCompetenciaAsociadaServiceRepo, Serializable {

	/**
	 * Inyeccion de ICompromisoEstrategicoRepo
	 */
	@Autowired
	IPddCompetenciaAsociadaRepo pddCompetenciaAsociadaRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * Metodo que permite retornar el Reposity correspondiente a la entidad PddCompetenciaAsociada
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad PddCompetenciaAsociada
	 */
	@Override
	public CrudRepository<PddCompetenciaAsociada, Long> getRepo() {
		return pddCompetenciaAsociadaRepository;
	}
	
	@Override
	public List<PddCompetenciaAsociada> obtenerPorIdSector(Long idSector) throws SpddExceptions {
		return pddCompetenciaAsociadaRepository.obtenerPorIdSector(idSector);
	}

	@Override
	public PddCompetenciaAsociada obtenerPorIdSectorYIdCompetencia(Long idSector, Long idLsCompetencia)	throws SpddExceptions {
		return pddCompetenciaAsociadaRepository.obtenerPorIdSectorYIdCompetencia(idSector, idLsCompetencia);
	}

	

}
