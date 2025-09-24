package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PotObra;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IPotObraRepo extends PagingAndSortingRepository<PotObra, Long>, Serializable {

	/**
	 * Metodo que permite obtener todos los PotObra correspondientes a un nivel pot
	 * 
	 * @param idNivelPot identificador del NivelPot por el cual se quiere filtrar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	@Query("SELECT po FROM PotObra po WHERE po.idNivelPot.idNivelPot= :nivelPot")
	public Page<PotObra> obtenerTodosPorIdNivelPot(@Param("nivelPot") Long idNivelPot, Pageable paginador);

	@Query("SELECT po FROM PotObra po")
	public Page<PotObra> obtenerTodosPotObraPorPot(@Param("idPot") Long idPot, Pageable pageable);

	/**
	 * MEtodo que permite obtener un PotObra por medio de su codigo y id nivel
	 * 
	 * @param codigo     string que reprsenta el codigo pro el cual se va a buscar
	 * @param idNivelPot Long que representa el identificador del nivel por el cual
	 *                   se va a buscar
	 * @return un objeto de tipo PotObra con la informacion correspondiente.
	 */
	@Query("SELECT po FROM PotObra po WHERE po.codigo=:codigo and po.idNivelPot.idNivelPot=:nivelPot")
	public PotObra obtenerPorCodigoYIdNivelPot(@Param("codigo") Long codigo, @Param("nivelPot") Long idNivelPot);
}
