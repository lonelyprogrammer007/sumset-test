package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla CompromisoEstrategico de la BD
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Transactional
@Repository
public interface ICompromisoEstrategicoRepo extends CrudRepository<CompromisoEstrategico, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar en BD un compromiso estrategico correspondiente a los identificadores de tematica
	 * y compromiso estrategico pasados como parametros
	 * @param idTematica Long que representa el idenficador de tematica
	 * @param idCompromisoEstrategico Long que representa el identificador de compromiso estrategico
	 * @return un objeto de tipo CompromisoEstrategico que contiene la informacion correspondiente o null en caso contrario
	 */
	@Query("SELECT ce FROM CompromisoEstrategico ce WHERE ce.idLsTematica.idArgumento= :tematica AND ce.idLsEstrategico.idArgumento= :compromisoEstrategico")
	public CompromisoEstrategico obtenerPorIdTematicaYIdCompromisoEstrategico(@Param("tematica") Long idTematica,@Param("compromisoEstrategico") Long idCompromisoEstrategico);

}
