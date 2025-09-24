package co.gov.sdp.spdd.data.irepositorio.bp;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvPmr;

@Transactional
@Repository
public interface IBpProyInvPmrRepo extends PagingAndSortingRepository<BpProyInvPmr, Long>, Serializable{
	
	/**
	 * Metodo que permite obtener un BpProyInvPmr por medio de los campos de unicidad de idIndMr e idProyInversion
	 * @param idIndMr Long que representa el identificador de MrIndicador
	 * @param idProyInversion Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvPmr con la informacion correspondiente.
	 */
	@Query("SELECT pipmr FROM BpProyInvPmr pipmr WHERE pipmr.idIndMr.idIndProxy = :idIndMr AND pipmr.idProyInversion.idProyInversion = :idProyectoInversion")
	public BpProyInvPmr obtenerPorIdIndMrYIdProyInversion(@Param("idIndMr") Long idIndMr,@Param("idProyectoInversion") Long idProyInversion);

}
