package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddPrbIndicadorServiceRepo extends IOperacionesBasicasFacade<PddPrbIndicador, Long> {
	
	/**
	 * Metodo que permite obtener la lista de PrbIndicador filtrado y paginados
	 * @param pddPrbIndicadorDTO objeto que contiene la informacion para filtrar y paginar
	 * @return un filtroDTO con la informacion correspondiente
	 */
	public FiltroDTO obtenerTodosFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO, Long inicio, Integer limite);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<PddPrbIndicador> obtenerPrbIndicadorPorProblematica(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param idIndicador
	 * @param idProblematica
	 * @return
	 */
	public PddPrbIndicador consultarPorIndicidicadorYProblematica(Long idIndicador, Long idProblematica);

}
