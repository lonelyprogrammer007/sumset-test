package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddPoliticaPublica;

@Transactional
@Repository
public interface IPddPoliticaPublicaRepo extends PagingAndSortingRepository<PddPoliticaPublica, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar las politicas publicas que no estan relacionadas con el proyecto de inversion
	 * @param idProyInversion Long que representa el proyecto de inversion que esta relacionado con las politicas publicas
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	@Query("SELECT pp FROM PddPoliticaPublica pp WHERE pp.idPolPub NOT IN (SELECT pipp.idPolPub.idPolPub FROM BpProyInvPolitica pipp WHERE pipp.idProyInversion.idProyInversion = :idProyectoInversion) ORDER BY pp.politica ASC")
	public List<PddPoliticaPublica> obtenerTodosSinRelacionConProyectoInversion(@Param("idProyectoInversion") Long idProyInversion);
	
	/**
	 * Metodo que permite consultar todas las politicas publicas ordenadas por el campo Politca ascendentemente
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	@Query("SELECT pp FROM PddPoliticaPublica pp ORDER BY pp.politica ASC")
	public List<PddPoliticaPublica> obtenerTodosOrdenadosPorNombrePolitica();
	
}
