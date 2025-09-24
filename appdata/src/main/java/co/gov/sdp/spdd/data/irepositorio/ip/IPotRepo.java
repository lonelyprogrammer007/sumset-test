package co.gov.sdp.spdd.data.irepositorio.ip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.Pot;

public interface IPotRepo extends CrudRepository<Pot, Long> {

	/**
	 * metodo que retorna un pdd respecto a un codigo
	 * @param codigoPot
	 * @return
	 */
	
	@Query("select p from Pot p where p.codigoPot=:codigoPot")
	public Pot obtenerPorCodigo(@Param("codigoPot") String codigoPot );
	
	/**
	 * Metodo que permite obtener el POT por estado
	 * @param estado estado del por por el cual se quiere filtar (Vigente | Finalizado)
	 * @return una lista con la informacion correspondiente
	 */
	@Query("select p from Pot p where LOWER(p.estado)= LOWER(:estado)")
	public List<Pot> obtenerPorEstado(@Param("estado") String estado);
	
	
	
}
