package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvIniciativaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvIniciativaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvIniciativa
 * 
 * @author DANIEL
 * @version 1.0 14/05/2020
 */
@Service
public class BpProyInvIniciativaRepo extends OperacionesBasicasFacade<BpProyInvIniciativa, Long> implements IBpProyInvIniciativaServiceRepo, Serializable{

	@Autowired
	IBpProyInvIniciativaRepo bpProyInvIniciativaRepository;
	
	@Override
	public CrudRepository<BpProyInvIniciativa, Long> getRepo() {
		return bpProyInvIniciativaRepository;
	}
	
	@Override
	public List<BpProyInvIniciativa> obtenerTodosPorIdProyInversion(Long idProyecto) {
		return bpProyInvIniciativaRepository.obtenerTodosPorIdProyInversion(idProyecto);
	}

	@Override
	public void eliminarTodos(List<BpProyInvIniciativa> listaBpProyInvIniciativa) {
		bpProyInvIniciativaRepository.deleteAll(listaBpProyInvIniciativa);
	}

	@Override
	public BpProyInvIniciativa obtenerPorIdIniciativaYIdProyInversion(Long idIniciativa, Long idProyecto) {
		return bpProyInvIniciativaRepository.obtenerPorIdIniciativaYIdProyInversion(idIniciativa, idProyecto);
	}

	@Override
	public List<BpProyInvIniciativa> obtenerTodosPorIdIniciativa(Long idIniciativa) {
		return bpProyInvIniciativaRepository.obtenerTodosPorIdIniciativa(idIniciativa);
	}
}
