package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaEtario;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IBpIniciativaEtarioServiceRepo extends IOperacionesBasicasFacade<BpIniciativaEtario, Long> {

	public List<BpIniciativaEtario> obtenerGrupoPorIniciativa(Long idIniciativa) throws SpddExceptions;

	public void eliminarTodos(List<BpIniciativaEtario> listaUbicaciones) throws SpddExceptions;
}
