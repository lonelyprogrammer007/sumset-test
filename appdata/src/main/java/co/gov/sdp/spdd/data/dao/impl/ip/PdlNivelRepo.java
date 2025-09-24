package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPdlNivelServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPdlNivelRepo;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository de la entidad PdlNivel
 * @author SEBASTIAN
 * @version 1.0 22/04/2020
 */
@Service
public class PdlNivelRepo extends OperacionesBasicasFacade<PdlNivel, Long> implements IPdlNivelServiceRepo, Serializable {


	/**
	 * Inyeccion de IPdlNivelRepo
	 */
	@Autowired
	IPdlNivelRepo pdlNivelRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	/**
	 * Metodo que permite retornar el Repository correspondiente a la entidad PdlNivel
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad PdlNivel
	 */
	@Override
	public CrudRepository<PdlNivel, Long> getRepo() {
		return pdlNivelRepository;
	}

	@Override
	public List<PdlNivel> obtenerPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions {
		return pdlNivelRepository.obtenerPdlNivelPorIdPlanLocal(idPlanLocal);
	}

	@Override
	public PdlNivel obtenerPdlNivelPorNivelYIdPlanLocal(Long nivel, Long idPlanLocal) throws SpddExceptions {
		return pdlNivelRepository.obtenerPdlNivelPorNivelYIdPlanLocal(nivel, idPlanLocal);
	}

}
