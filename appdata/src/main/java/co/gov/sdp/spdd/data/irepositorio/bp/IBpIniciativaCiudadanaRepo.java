package co.gov.sdp.spdd.data.irepositorio.bp;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpIniciativaCiudadana;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Transactional
@Repository
public interface IBpIniciativaCiudadanaRepo extends CrudRepository<BpIniciativaCiudadana, Long>,Serializable {
	
	
	
	/**
	 * Metodo que permite obtener todas las BpIniciativasCiudadanas que estan relacionadas con el proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto por el cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	@Query("SELECT ic FROM BpIniciativaCiudadana ic WHERE ic.idIniciativa IN (SELECT pii.idIniciativa.idIniciativa FROM BpProyInvIniciativa pii WHERE pii.idProyInversion.idProyInversion = :proyecto)")
	public List<BpIniciativaCiudadana> obtenerTodosRelacionadasConProyectoInversion(@Param("proyecto") Long idProyecto);

	

}
