package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvEtnicoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPoblacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvEtnicoRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvPoblacionRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvEtnico
 * 
 * @author LUIS FIGUEORA
 * @version 1.0 28/05/2020
 */
@Service
public class BpProyInvEtnicoRepo extends OperacionesBasicasFacade<BpProyInvEtnico, Long>
		implements IBpProyInvEtnicoServiceRepo, Serializable {

	@Autowired
	IBpProyInvEtnicoRepo IBpProyInvEtnicoRepository;

	@Override
	public Page<BpProyInvEtnico> obtenerTodosProyInvEtnicoAsociadosAProyectoInversion(Long idPoblacion,
			Pageable paginador) throws SpddExceptions {

		return IBpProyInvEtnicoRepository.obtenerTodosPorIdProyectoInversion(idPoblacion, paginador);
	}

	@Override
	public CrudRepository<BpProyInvEtnico, Long> getRepo() {

		return IBpProyInvEtnicoRepository;
	}

	@Override
	public BpProyInvEtnico obtenerPorIdLsEtnicoYIdPoblacion(Long idLsEtnico, Long idPoblacion) {

		return IBpProyInvEtnicoRepository.obtenerPorIdLsEtnicoYIdPoblacion(idLsEtnico, idPoblacion);
	}

}
