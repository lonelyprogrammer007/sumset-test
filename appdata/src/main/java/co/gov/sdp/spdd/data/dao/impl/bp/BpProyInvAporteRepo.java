package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAporteServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvAporteRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvAporte
 * 
 * @author DANIEL
 * @version 1.0 16/04/2020
 */
@Service
public class BpProyInvAporteRepo extends OperacionesBasicasFacade<BpProyInvAporte, Long> implements IBpProyInvAporteServiceRepo, Serializable {

	@Autowired
	IBpProyInvAporteRepo bpProyInvAporteRepository;
	
	@Override
	public CrudRepository<BpProyInvAporte, Long> getRepo() {
		return bpProyInvAporteRepository;
	}
	
	@Override
	public BpProyInvAporte obtenerPorIdAporteYIdProyInversion(Long idAporte, Long idProyInversion) {
		return bpProyInvAporteRepository.obtenerPorIdAporteYIdProyInversion(idAporte, idProyInversion);
	}

	@Override
	public Page<BpProyInvAporte> obtenerTodosPorIdProyInversionPaginado(Long idProyInversion, Pageable paginador) {
		return bpProyInvAporteRepository.obtenerTodosPorIdProyInversionPaginado(idProyInversion, paginador);
	}

	@Override
	public List<BpProyInvAporte> obtenerTodosCargadoArchivoPorIdProyInversion(Long idProyInversion) {
		return bpProyInvAporteRepository.obtenerTodosCargadoArchivoPorIdProyInversion(idProyInversion);
	}

	@Override
	public void eliminarTodos(List<BpProyInvAporte> listaBpProyInvAporte) {
		bpProyInvAporteRepository.deleteAll(listaBpProyInvAporte);		
	}

	

}
