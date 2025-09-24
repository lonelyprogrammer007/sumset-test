package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IInformacionPresupuestalServiceRepo extends IOperacionesBasicasFacade<InformacionPresupuestal, Long>, Serializable  {

	/**
	 * 
	 * @param informacionPresupuestalDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(InformacionPresupuestalDTO informacionPresupuestalDTO, Long inicio, Integer limite)
			throws SpddExceptions;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<InformacionPresupuestal> obtenerInformacionPresupuestalPorPlanDesarrollo(Long id)
			throws SpddExceptions;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<InformacionPresupuestal> obtenerInformacionPresupuestalPorEntidad(Long id) throws SpddExceptions;

	public List<InformacionPresupuestal> obtenerPorArchivo(Long idArchivo);
	
	/**
	 * Metodo que permite obtener una informacion presupuestal por medio de la combinacion de codigoEntidad (codigoDistrital), mes y año
	 * @param codigoEntidad String que represneta el codigo de la entidad o codigo distrital
	 * @param mes Long que representa el numero del mes
	 * @param year Long que represneta el año
	 * @return un objeto de tipo InformacionPresupuestal con la informacion correspondiente
	 */
	public InformacionPresupuestal obtenerPorCodigoEntidadYMesYYear(String codigoEntidad,Long mes,Long year);

}
