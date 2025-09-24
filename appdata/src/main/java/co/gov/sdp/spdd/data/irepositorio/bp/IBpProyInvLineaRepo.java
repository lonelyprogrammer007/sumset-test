package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;

/**
 * 
 * @author DANIEL
 *
 */
@Transactional
@Repository
public interface IBpProyInvLineaRepo extends CrudRepository<BpProyInvLinea, Long> {
	
	/**
	 * Metodo que permite obtener una lista de BpProyInvLinea relacionado a un proyecto de inversion
	 * @param idProyecto Long que representa el identificador por le cual se va a buscar los BpProyInvTipo 
	 * @return una lista BpProyInvLinea con la informacion correspondiente
	 */
	@Query("SELECT pil FROM BpProyInvLinea pil WHERE pil.idProyInversion.idProyInversion=:idProyecto")
	public List<BpProyInvLinea> obtenerPorIdProyectoInversion(@Param("idProyecto") Long idProyecto);
	
	/**
	 * Metodo que permite obtener un BpProyInvLinea por medio de la unicidad entre el identificador de la linea de inversion y 
	 * el identificador del proyecto de inversion
	 * @param idLineaInversion Long que representa el identificador de la linea de inversion
	 * @param idProyecto Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvLinea con la informacion correspondiente
	 */
	@Query("SELECT pil FROM BpProyInvLinea pil WHERE pil.idLineaInversion.idLineaInversion = :idLinea AND pil.idProyInversion.idProyInversion=:idProyecto")
	public BpProyInvLinea obtenerPorIdLineaInversionYIdProyInversion(@Param("idLinea") Long idLineaInversion, @Param("idProyecto") Long idProyecto);

}
