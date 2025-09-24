package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;

@Transactional
@Repository
public interface IPotProyectoInstrumentoRepo extends CrudRepository<PotProyectoInstrumento, Long>, Serializable  {

	/**
	 * 
	 * @param idPotInstrumento
	 * @param idPotProyecto
	 * @return
	 */
	@Query("SELECT ppi FROM PotProyectoInstrumento ppi WHERE ppi.idPotInstrumento.idInstrumentoPot = :idPotInstrumento AND ppi.idPotProyecto.idObraProyPot = :idPotProyecto")
	public PotProyectoInstrumento findByIdLsPotObraAndIdLsPotInstrumento(@Param("idPotInstrumento") Long idPotInstrumento, @Param("idPotProyecto") Long  idPotProyecto);
	
}
