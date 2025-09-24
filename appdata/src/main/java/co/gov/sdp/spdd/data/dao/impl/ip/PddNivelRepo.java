package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddNivelServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddNivelRepo;
import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository de la entidad PddNivel
 * @author DANIEL
 * @version 1.0 03/03/2020
 */
@Service
public class PddNivelRepo extends OperacionesBasicasFacade<PddNivel, Long> implements IPddNivelServiceRepo, Serializable {

	/**
	 * Inyeccion de IPddNivelRepo
	 */
	@Autowired
	IPddNivelRepo pddNivelRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	/**
	 * Metodo que permite retornar el Repository correspondiente a la entidad PddNivel
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad PddNivel
	 */
	@Override
	public CrudRepository<PddNivel, Long> getRepo() {		
		return pddNivelRepository;
	}

	@Override
	public List<PddNivel> obtenerPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		return pddNivelRepository.obtenerPddNivelPorIdPlanDesarrollo(idPlan);
	}

	@Override
	public PddNivel obtenerPorNivelYIdPlanDesarrollo(Long nivel, Long idPlan) throws SpddExceptions {
		return pddNivelRepository.obtenerPddNivelPorNivelYIdPlanDesarrollo(nivel, idPlan);
	}

	@Override
	public List<PddNivel> obtenerPddNivelDesbalanceadosPorNivelYIdPlanDesarrollo(Long nivel, Long idPlanDesarrollo) {
		return pddNivelRepository.obtenerPddNivelDesbalanceadosPorNivelYIdPlanDesarrollo(nivel, idPlanDesarrollo);
	}

}
