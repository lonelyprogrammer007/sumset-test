package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddNivelAtributoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddNivelAtributoRepo;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Service
public class PddNivelAtributoRepo extends OperacionesBasicasFacade<PddNivelAtributo, Long> implements IPddNivelAtributoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPddNivelAtributoRepo pddNivelAtributoRepository;
	
	/**
	 * 
	 */
	@Override
	public List<PddNivelAtributo> pddNivelAtributoObtenerLibres() {
		return pddNivelAtributoRepository.pddNivelAtributoObtenerLibres();
	}

	/**
	 * 
	 */
	@Override
	public CrudRepository<PddNivelAtributo, Long> getRepo() {
		return pddNivelAtributoRepository;
	}

	@Override
	public PddNivelAtributo obtenerPorNumeroYIdPddNivel(Long numero, Long idPddNivel, Long idAtributoPadre) {
		return pddNivelAtributoRepository.obtenerPorNumeroYIdPddNivel(numero, idPddNivel, idAtributoPadre);
	}

	@Override
	public Page<PddNivelAtributo> obtenerTodosPorIdPddNivelPaginado(Long idPddNivel,Pageable paginador) {
		return pddNivelAtributoRepository.obtenerTodosPorIdPddNivelPaginado(idPddNivel, paginador);
	}

	@Override
	public PddNivelAtributo obtenerPorDenominacionYIdPddNivel(String denominacion, Long idPddNivel) {
		return pddNivelAtributoRepository.obtenerPorDenominacionYIdPddNivel(denominacion, idPddNivel);
	}

	@Override
	public Page<PddNivelAtributo> obtenerTodosPorIAtributoPadrePaginado(Long idAtributoPadre, Pageable paginador) {
		return pddNivelAtributoRepository.obtenerTodosPorIAtributoPadrePaginado(idAtributoPadre,paginador);
	}

	@Override
	public List<PddNivelAtributo> obtenerTodosDesbalanceados(Long idPlanDesarrollo) {
		return pddNivelAtributoRepository.obtenerTodosDesbalanceados(idPlanDesarrollo);
	}

	@Override
	public List<PddNivelAtributo> obtenerTodosPorIdPddNivel(Long idPddNivel) {
		return pddNivelAtributoRepository.obtenerTodosPorIdPddNivel(idPddNivel);
	}

	@Override
	public List<PddNivelAtributo> obtenerTodosPorIAtributoPadre(Long idAtributoPadre) {
		return pddNivelAtributoRepository.obtenerTodosPorIAtributoPadre(idAtributoPadre);
	}

	@Override
	public PddNivelAtributo obtenerPorNumeroDePrimerNivelDeIdPlanDesarrollo(Long numero, Long idPlanDesarrollo) {
		return pddNivelAtributoRepository.obtenerPorNumeroDePrimerNivelDeIdPlanDesarrollo(numero, idPlanDesarrollo);
	}

	@Override
	public PddNivelAtributo obtenerPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(Long numero, Long codigoNumero,
			Long idAtributoPadre) {
		return pddNivelAtributoRepository.obtenerPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(numero, codigoNumero, idAtributoPadre);
	}

	@Override
	public Long obtenerPonderacionTotalDeTodosPorIdPddNivel(Long idPddNivel) {
		return pddNivelAtributoRepository.obtenerPonderacionTotalDeTodosPorIdPddNivel(idPddNivel);
	}

	@Override
	public Long obtenerPonderacionTotalDeTodosPorIAtributoPadre(Long idAtributoPadre) {
		return pddNivelAtributoRepository.obtenerPonderacionTotalDeTodosPorIAtributoPadre(idAtributoPadre);
	}
	
}
