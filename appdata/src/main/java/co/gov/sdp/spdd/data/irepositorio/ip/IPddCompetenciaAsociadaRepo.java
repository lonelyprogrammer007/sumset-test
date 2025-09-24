package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PDD_COMPETENCIA_ASOCIADA de la BD
 * @author DANIEL
 * @version 1.0 19/02/2020
 */
@Transactional
@Repository
public interface IPddCompetenciaAsociadaRepo extends CrudRepository<PddCompetenciaAsociada, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar las PddCompetenciaAsociada relacionados a un sector
	 * @param idSector Long que representa el identificador del sector por el cual se va a buscar
	 * @return una lista de tipo PddCompetenciaAsociada correspondiente a la busqueda o null
	 */
	@Query("SELECT ca FROM PddCompetenciaAsociada ca WHERE ca.idLsSector.idArgumento= :sector")
	public List<PddCompetenciaAsociada> obtenerPorIdSector(@Param("sector") Long idSector)throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar un PddCompetenciaAsociada que corresponda a los identificadores del secto y LsCompetencia que se pasan como parametro
	 * @param idSector Long que representa el identificador del sector por el cual se va a buscar o filtrar
	 * @param idLsCompetencia Long que representa el identificador de LsCompetencia por el cual se va a buscar o filtrar
	 * @return un objeto de tipo PddCompetenciaAsociada con la informacion correspodiente
	 * @throws SpddExceptions
	 */
	@Query("SELECT ca FROM PddCompetenciaAsociada ca WHERE ca.idLsSector.idArgumento= :sector and ca.idLsCompetencia.idArgumento= :competencia")
	public PddCompetenciaAsociada obtenerPorIdSectorYIdCompetencia(@Param("sector") Long idSector, @Param("competencia") Long idLsCompetencia) throws SpddExceptions;
}
