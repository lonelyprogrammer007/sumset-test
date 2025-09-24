package co.gov.sdp.spdd.data.dao.impl.bp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaEtarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpIniciativaEtarioRepo;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaEtario;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaUbicacion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Service
public class BpIniciativaEtarioRepo extends OperacionesBasicasFacade<BpIniciativaEtario, Long>
		implements IBpIniciativaEtarioServiceRepo {

	@Autowired
	IBpIniciativaEtarioRepo etarioRepository;

	@Override
	public CrudRepository<BpIniciativaEtario, Long> getRepo() {
		return etarioRepository;
	}

	@Override
	public List<BpIniciativaEtario> obtenerGrupoPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return etarioRepository.obtenerGrupoEtarioPorIniciativa(idIniciativa);
	}

	@Override
	public void eliminarTodos(List<BpIniciativaEtario> listaUbicaciones) throws SpddExceptions {
		etarioRepository.deleteAll(listaUbicaciones);
	}

}
