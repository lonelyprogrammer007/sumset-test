package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAnualizaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvAnualizaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad ProyInvAnualiza
 * 
 * @author Jefferson Arenas
 * @version 1.0 02/06/2020
 */
@Service
public class BpProyInvAnualizaRepo extends OperacionesBasicasFacade<BpProyInvAnualiza, Long>
		implements IBpProyInvAnualizaServiceRepo, Serializable {

	@Autowired
	IBpProyInvAnualizaRepo iBpProyInvAnualizaRepo;

	@Override
	public CrudRepository<BpProyInvAnualiza, Long> getRepo() {
		return iBpProyInvAnualizaRepo;
	}

	@Override
	public Page<BpProyInvAnualiza> obtenerPorIdTodosProyInvAnualiza(Long idFuente, Pageable paginador) {
		return iBpProyInvAnualizaRepo.obtenerPorIdTodosProyInvAnualiza(idFuente, paginador);
	}

	@Override
	public void eliminarTodosPorIdFUente(List<BpProyInvAnualiza> data) {

		iBpProyInvAnualizaRepo.deleteAll(data);
	}

	@Override
	public List<BpProyInvAnualiza> obtenerPorIdTodosProyInvAnualizacion(Long idFuente) {
		return iBpProyInvAnualizaRepo.obtenerPorIdTodosProyInvAnualizacion(idFuente);
	}

}
