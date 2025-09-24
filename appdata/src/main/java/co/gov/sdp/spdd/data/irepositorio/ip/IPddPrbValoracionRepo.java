package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PddPrbValoracion de la BD
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Transactional
@Repository
public interface IPddPrbValoracionRepo extends CrudRepository<PddPrbValoracion, Long>, Serializable {
	
	/**
	 * Metodo que permite realizar la cosulta de una PddPrbValoracion a la BD por medio del identificador de la problematica y el momento
	 * @param idProblematica Long que representa el identificador de la problematica por la cual se va a buscar o filtrar
	 * @param momento Long que representa el valor del momento (1-antes, 2-Durante, 3-Despues)
	 * @return un objeto de tipo PddPrbValoracion con la iformacion correspondiente.
	 * @throws SpddExceptions
	 */
	@Query("SELECT v FROM PddPrbValoracion v WHERE v.idProblematica.idProblematica= :problematica AND v.momento= :momento")
	public PddPrbValoracion obtenerPorIdProblematicaYMomento(@Param("problematica") Long idProblematica,@Param("momento") Long momento) throws SpddExceptions;

	
}
