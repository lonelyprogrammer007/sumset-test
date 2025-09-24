package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPddServiceRepo extends IOperacionesBasicasFacade<Pdd, Long>, Serializable{

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public List<Pdd> obtenerTodosPdd() throws SpddExceptions;
	
	/**
	 * Metodo que permite filtrar por algun campo en especifico de Pdd
	 * @param pddDTO objeto DTO de tipo PddDTO que contiene la informacion para filtrar.
	 * @param inicio long que indica la pagina
	 * @param limite Integer que indica la cantidad de elementos a filtrar
	 * @return un objeto de tipo FiltroDTO con la informacion filtrada
	 * @throws SpddExceptions Excepcion propia que puede ser lanzada
	 */
	public FiltroDTO filtrarPorCampo(PddDTO pddDTO, Long inicio, Integer limite) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener los Pdds por un estado en especifico
	 * @param idEstado Long que representa el estado por el cual se piensa filtrar o discriminar los pdds
	 * @return una lista de Pdd que contiene todos los planes que corresponden al estado o null en caso de que
	 * no exista ninguna pdd
	 * @throws SpddExceptions
	 */
	public List<Pdd> obtenerPddsPorEstado(Long idEstado) throws SpddExceptions;
	
	/**
     * Metodo que permite consultar un PDD por el identifcador
     * @param idPlan Long que represneta el identificador del plan de desarrollo a buscar
     * @return un objeto de Pdd con la informacion correspondiente
     */
	public Pdd obtenerPorId(Long idPlan);
	
	/**
     * Metodo que permite obtenre todos los PDD en orden ascendente
     * @return
     */
    public List<Pdd> obtenerTodosOrdenadosASC();
	
	
}
