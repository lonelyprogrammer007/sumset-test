package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IInformacionPresupuestalRepo extends CrudRepository<InformacionPresupuestal, Long>, Serializable {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT c FROM InformacionPresupuestal c WHERE c.idPlanDesarrollo.idPlanDesarrollo = :id")
	public List<InformacionPresupuestal> obtenerInformacionPresupuestalPorPlanDesarrollo(@Param("id") Long id)
			throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT c FROM InformacionPresupuestal c WHERE c.codigoDistrital.codigoEntidad = :id")
	public List<InformacionPresupuestal> obtenerInformacionPresupuestalPorEntidad(@Param("id") Long id) throws SpddExceptions;


	@Query("SELECT c FROM InformacionPresupuestal c WHERE c.idArchivo.idArchivo = :id")
    public List<InformacionPresupuestal> obtenerArchivoInfo(@Param("id") Long archivo);
	
	/**
	 * Metodo que permite obtener una informacion presupuestal por medio de la combinacion de codigoEntidad (codigoDistrital), mes y año
	 * @param codigoEntidad String que represneta el codigo de la entidad o codigo distrital
	 * @param mes Long que representa el numero del mes
	 * @param year Long que represneta el año
	 * @return un objeto de tipo InformacionPresupuestal con la informacion correspondiente
	 */
	@Query("SELECT ip FROM InformacionPresupuestal ip WHERE ip.codigoDistrital.codigoEntidad = :entidad AND ip.mes = :mes AND ip.year = :year")
	public InformacionPresupuestal obtenerPorCodigoEntidadYMesYYear(@Param("entidad") String codigoEntidad,@Param("mes") Long mes,@Param("year") Long year);
	

}
