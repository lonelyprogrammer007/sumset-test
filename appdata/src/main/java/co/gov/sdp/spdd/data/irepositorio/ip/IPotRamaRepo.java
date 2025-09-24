package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PotRama;

public interface IPotRamaRepo extends CrudRepository<PotRama, Long> {

	@Query("select p from PotRama p where p.idPot.idPot =:idPot order by p.numeroRama asc")
	public List<PotRama> obtenerPotRamaPorIdPot(@Param("idPot") Long idPot);
	
	@Query("select p from PotRama p where p.idPot.idPot =:idPot order by p.numeroRama desc")
	public List<PotRama> obtenerPotRamaPorIdPotNumeroRamaDesc(@Param("idPot") Long idPot);
	
}
