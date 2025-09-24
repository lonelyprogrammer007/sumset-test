package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PotNivel;

public interface IPotNivelRepo extends CrudRepository<PotNivel, Long> {

	
	@Query("select p from PotNivel p where p.idRamaPot.idRamaPot=:idRamaPot order by p.numeroNivel asc")
	public List<PotNivel> obtenerPotNivelPorIdRamaPot(@Param("idRamaPot") Long idRamaPot);
	
	@Query("select p from PotNivel p where p.idRamaPot.idRamaPot=:idRamaPot order by p.numeroNivel desc")
	public List<PotNivel> obtenerPotNivelPorIdRamaPotDesc(@Param("idRamaPot") Long idRamaPot);
	
	@Query("select p from PotNivel p where p.idNivelPadre.idNivelPot=:idNivelPadre order by p.numeroNivel asc")
	public List<PotNivel> obtenerPotNivelPorIdNivelPadre(@Param("idNivelPadre") Long idNivelPot);
	
	@Query("select p from PotNivel p where p.idNivelPadre.idNivelPot=:idNivelPadre order by p.numeroNivel desc")
	public List<PotNivel> obtenerPotNivelPorIdNivelPadreDesc(@Param("idNivelPadre") Long idNivelPot);
	
	
}
