package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPoblacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvProductoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvPoblacionRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvProductoRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;
import co.gov.sdp.spdd.data.model.view.VistaBpIniciativaCiudadana;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvPoblacion
 * @author LUIS FIGUEROA
 * @version 1.0 28/05/2020
 */
@Service
public class BpProyInvPoblacionRepo extends OperacionesBasicasFacade<BpProyInvPoblacion, Long> implements IBpProyInvPoblacionServiceRepo, Serializable{
	
	@Autowired
	transient EntityManager em;
	
	@Autowired
	IBpProyInvPoblacionRepo bpProyInvPoblacionRepository;
	
	@Override
	public Page<BpProyInvPoblacion> obtenerTodosGruposEtarios(Long idProyectoInversion, Pageable paginador) throws SpddExceptions{
		
		return bpProyInvPoblacionRepository.obtenerTodosPorIdProyectoInversion(idProyectoInversion, paginador);
	}

	@Override
	public CrudRepository<BpProyInvPoblacion, Long> getRepo() {
		
		return bpProyInvPoblacionRepository;
	}

	@Override
	public BpProyInvPoblacion obtenerPorIdLsEtarioYIdProyInv(Long idLsEtario, Long idProyInv) {
		
		return bpProyInvPoblacionRepository.obtenerPorIdLsEtarioYIdProyInv(idLsEtario, idProyInv);
	}


}
