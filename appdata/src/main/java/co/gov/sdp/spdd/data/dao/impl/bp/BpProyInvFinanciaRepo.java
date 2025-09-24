package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvFinanciaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvFinanciaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvFinancia
 * 
 * @author Jefferson Arenas 
 * @version 1.0 02/06/2020
 */
@Service
public  class BpProyInvFinanciaRepo  extends OperacionesBasicasFacade<BpProyInvFinancia, Long> implements IBpProyInvFinanciaServiceRepo, Serializable{
	
	@Autowired
	IBpProyInvFinanciaRepo iBpProyInvFinanciaRepo;
	
	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<BpProyInvFinancia, Long> getRepo() {
		return iBpProyInvFinanciaRepo;
	}

	@Override
	public Page<BpProyInvFinancia> obtenerPorIdTodosProyInvFiancia(Long idProyInversion, Pageable paginador) {

		
		return iBpProyInvFinanciaRepo.obtenerPorIdTodosProyInvFiancia(idProyInversion, paginador);
	}

	@Override
	public BpProyInvFinancia consultarProyInvFinanciaPorIdLSFuYIdProy(Long idLsFUente, Long idProyectoInv) {
		return iBpProyInvFinanciaRepo.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente, idProyectoInv);
	}

	@Override
	public BpProyInvFinancia consultarProyInvFinanciaPorId(Long idFuente) {
		return iBpProyInvFinanciaRepo.consultarProyInvFinanciaPorId(idFuente);
	}



	

	

}
