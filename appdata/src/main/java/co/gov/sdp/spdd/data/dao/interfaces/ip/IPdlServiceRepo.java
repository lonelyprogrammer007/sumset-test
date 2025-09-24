package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPdlServiceRepo extends IOperacionesBasicasFacade<Pdl, Long>, Serializable{

	/**
	 * Metodo que permite filtrar por algun campo en especifico de Pdl
	 * @param pddDTO objeto DTO de tipo PdlDTO que contiene la informacion para filtrar.
	 * @param inicio long que indica la pagina
	 * @param limite Integer que indica la cantidad de elementos a filtrar
	 * @return un objeto de tipo FiltroDTO con la informacion filtrada
	 * @throws SpddExceptions Excepcion propia que puede ser lanzada
	 */
	public FiltroDTO filtrarPorCampo(PdlDTO pdlDTO, Long inicio, Integer limite) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener los Pdls por un estado en especifico
	 * @param idEstado Long que representa el estado por el cual se piensa filtrar o discriminar los pdds
	 * @return una lista de Pdl que contiene todos los planes que corresponden al estado o null en caso de que
	 * no exista ninguna pdd
	 * @throws SpddExceptions
	 */
	public List<Pdl> obtenerPdlsPorEstado(Long idEstado) throws SpddExceptions;
	
	/**
     * Metodo que permite consultar los PDL de una entidad
     * @param codigoEntidad Long que representa el codigo de la entidad por el cual se quiere hacer la busqueda
     * @return una lista de entidades PDL correspondientes a la busqueda o null en caso contrario
     */
    public List<Pdl> obtenerPorLsEstadoPlanYCodigoEntidad(String resultado, String codigoEntidad);
	
}
