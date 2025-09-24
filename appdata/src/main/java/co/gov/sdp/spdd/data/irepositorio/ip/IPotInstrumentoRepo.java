package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PotInstrumento;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IPotInstrumentoRepo extends PagingAndSortingRepository<PotInstrumento, Long>, Serializable  {
	
	/**
	 * Metodo que permite obtener un PotInstrumento por el codigo y por identificador del pot
	 * @param codigo String que representa el codigo del PotInstrumento
	 * @param idPot Long que representa el identificador del pot
	 * @return un objeot de tipo PotInstrumento con la informacion correspondiente
	 */
    @Query("SELECT pi FROM PotInstrumento pi WHERE pi.codigo= :codigo AND pi.idPot.idPot=:pot")
	public PotInstrumento obtenerPorCodigoYIdPot(@Param("codigo") Long codigo, @Param("pot") Long idPot);
	
}
