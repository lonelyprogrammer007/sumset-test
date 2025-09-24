package co.gov.sdp.spdd.data.irepositorio.bp;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;

@Transactional
@Repository
public interface IBpProyInvPoliticaRepo extends PagingAndSortingRepository<BpProyInvPolitica, Long>, Serializable {
	
	/**
	 * Metodo que permite obtener un BpProyInvPolitica por medio de la llave de unicidad entre el identificador de la politica
	 * y el identificador del proyecto de inversion
	 * @param idPolPub Long que representa el identificador de la politica publica porl a que se va filtrar
	 * @param idProyInversion Long que represneta el identiricador del proyecto de inversion por el cual se va filtrar
	 * @return Un objeto de tipo BpProyInvPolitica que contiene la informacion correspondiente
	 */
	@Query("SELECT pipp FROM BpProyInvPolitica pipp WHERE pipp.idPolPub.idPolPub = :idPolitica AND pipp.idProyInversion.idProyInversion = :idProyectoInversion")
	public BpProyInvPolitica obtenerPorIdPolPubYIdProyInversion(@Param("idPolitica")Long idPolPub,@Param("idProyectoInversion") Long idProyInversion);
}
