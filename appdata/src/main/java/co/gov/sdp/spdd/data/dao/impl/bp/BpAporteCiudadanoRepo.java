package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpAporteCiudadanoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpAporteCiudadanoRepo;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpAporteCiudadano
 * 
 * @author DANIEL
 * @version 1.0 16/04/2020
 */
@Service
public class BpAporteCiudadanoRepo extends OperacionesBasicasFacade<BpAporteCiudadano, Long> implements IBpAporteCiudadanoServiceRepo, Serializable {

	@Autowired
	IBpAporteCiudadanoRepo bpAporteCiudadanoRepository;
	
	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<BpAporteCiudadano, Long> getRepo() {
		return bpAporteCiudadanoRepository;
	}
	
	@Override
	public List<BpAporteCiudadano> obtenerTodosSinRelacionConProyectoInversion(Long idProyecto) {
		return bpAporteCiudadanoRepository.obtenerTodosSinRelacionConProyectoInversion(idProyecto);
	}

	@Override
	public BpAporteCiudadano obtenrePorAccionYFuenteYIdNivelPdd(String accion, String fuente, Long idPddNivelAtributo) {
		return bpAporteCiudadanoRepository.obtenrePorAccionYFuenteYIdNivelPdd(accion, fuente, idPddNivelAtributo);
	}

	@Override
	public List<BpAporteCiudadano> obtenerTodosCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto) {
		return bpAporteCiudadanoRepository.obtenerTodosCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
	}

	@Override
	public List<BpAporteCiudadano> obtenerTodosCargadosPorArchivos() {		
		return bpAporteCiudadanoRepository.obtenerTodosCargadosPorArchivos();
	}

	@Override
	public List<BpAporteCiudadano> obtenerTodosOrdenadorPorConsecutivoASC() {
		return bpAporteCiudadanoRepository.obtenerTodosOrdenadorPorConsecutivoASC();
	}
}
