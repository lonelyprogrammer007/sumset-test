package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PddCompromisoEspecifico de la BD
 * 
 * @author DANIEL
 * @version 1.0 10/03/2020
 */
@Transactional
@Repository
public interface IPddCompromisoEspecificoRepo extends CrudRepository<PddCompromisoEspecifico, Long>,Serializable {
	
	/**
	 * Metodo que permite consultar en BD un PddCompromisoEspecifico correspondiente a la relacion entre los campos de idCompromiso y descripcion
	 * @param idCompromiso Long que representa el identificador del pddCompromiso por el cual se quiere filtar o buscar
	 * @param descripcion String que representa la descripccion del pddCompromisoEspecifico por el cual se quiere filtrar o buscar.
	 * @return un objeto de tipo PddCompromisoEspecifico con la informacion correspondiente o null en caso contrario.
	 */
	@Query("SELECT ce FROM PddCompromisoEspecifico ce WHERE ce.idCompromiso.idCompromiso= :compromiso AND lower(ce.descripcion)= lower(:descripcion)")
	public PddCompromisoEspecifico obtenerPorIdCompromisoYDescripcion(@Param("compromiso") Long idCompromiso,@Param("descripcion") String descripcion);
	
	/**
	 * Metodo que permite consultar en BD los PddCompromisoEspecifico correspondientes a un pddCompromiso
	 * @param idCompromiso Long que representa el identificador del pddCompromiso por el cual se quiere buscar o filtrar
	 * @return una lista de PddCompromisoEspecifico con la informacion correspondiente
	 */
	@Query("SELECT ce FROM PddCompromisoEspecifico ce WHERE ce.idCompromiso.idCompromiso= :compromiso")
	public List<PddCompromisoEspecifico> obtenerPorIdCompromiso(@Param("compromiso") Long idCompromiso);
	
}
