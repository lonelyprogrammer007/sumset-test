package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PotObraEntidad de la BD
 * 
 * @author DANIEL
 * @version 1.0 30/04/2020
 */
@Transactional
@Repository
public interface IPotObraEntidadRepo extends CrudRepository<PotObraEntidad, Long>, Serializable {
	
	/**
	 * Metodo que pemite obtener todos los PotObraEntidad por medio del potObra
	 * @param idPotObra Long que representa el identificador del PotObra por el cual se va a buscar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	@Query("SELECT poe FROM PotObraEntidad poe WHERE poe.idObraProyPot.idObraProyPot=:potObra")
	public List<PotObraEntidad> obtenerTodosPorIdPotObra(@Param("potObra") Long idPotObra);
	
	/**
	 * Metodo que permite obtener el PotObraEntidad correspondiente a la entidad y PotObra indicados
	 * @param codigoEntidad String que representa el codigo de la entidad
	 * @param idPotObra Long que representa el identificador de la potObra
	 * @return un objeto de tipo PotObraEntidad con la informacion correspondiente
	 */
	@Query("SELECT poe FROM PotObraEntidad poe WHERE  poe.codigoEntidad.codigoEntidad=:codigoEntidad AND poe.idObraProyPot.idObraProyPot=:potObra")
	public PotObraEntidad obtenerPotCodigoEntidadYIdPotObra(@Param("codigoEntidad") String codigoEntidad, @Param("potObra") Long idPotObra);

}
