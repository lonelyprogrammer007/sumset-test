package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotObraServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotObraRepo;
import co.gov.sdp.spdd.data.model.ip.PotObra;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class PotObraRepo extends OperacionesBasicasFacade<PotObra, Long> implements IPotObraServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPotObraRepo potObraRepositorio;

	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<PotObra, Long> getRepo() {
		return potObraRepositorio;
	}

	@Override
	public Page<PotObra> obtenerTodosPaginadoPorIdNivelPot(Long idNivelPot, Pageable paginador) {
		return potObraRepositorio.obtenerTodosPorIdNivelPot(idNivelPot, paginador);
	}

	@Override
	public PotObra obtenerPorCodigoYIdNivelPot(Long codigo, Long idNivelPot) {
		return potObraRepositorio.obtenerPorCodigoYIdNivelPot(codigo, idNivelPot);
	}

	@Override
	public Page<PotObra> obtenerPotObraPorPot(Long pot, Pageable pageable) throws SpddExceptions {
		return potObraRepositorio.obtenerTodosPotObraPorPot(pot, pageable);
	}

}
