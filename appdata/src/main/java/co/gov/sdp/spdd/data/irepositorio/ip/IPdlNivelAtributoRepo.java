package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PdlNivelAtributo de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 22/04/2020
 */
@Transactional
@Repository
public interface IPdlNivelAtributoRepo extends PagingAndSortingRepository<PdlNivelAtributo, Long>, Serializable {

	/**
	 * Permite obtener todos los PdlNivelAtributo de un nivel en especifico en orden ascendente
	 * @param idPdlNivel Long que representa el identificador del nivel que se quieren obtener los atributos
	 * @return una lista de tipo PdlNivelAtributo
	 */
	@Query("SELECT pdlna FROM PdlNivelAtributo pdlna WHERE pdlna.idPdlNivel.idPdlNivel=:pdlNivel")
	public Page<PdlNivelAtributo> obtenerTodosPorIdPdlNivelAtributoPaginado(@Param("pdlNivel") Long idPdlNivel, Pageable paginador);
	
    /**
     * Permite obtener todos los PdlNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return una lista de tipo PdlNivelAtributo
     */
    @Query("SELECT pdlna FROM PdlNivelAtributo pdlna WHERE pdlna.idAtributoPadre.idAtributo=:atributoPadre")
    public Page<PdlNivelAtributo> obtenerTodosPorIdAtributoPadrePaginado(@Param("atributoPadre") Long idAtributoPadre, Pageable paginador);
    
    /**
     * Metodo que permite obtener un PdlNivelAtributo por medio de la denominacion y del identificador del nivel al que esta relacionado
     * @param String que representa la denominacion del nivel atributo
     * @param idPdlNivel Long que representa el identificador del pdlNivel al que esta asociado el atributo nivel
     * @return un objeto de tipo PdlNivelAtributo con la informacion correspondiente.
     */
    @Query("SELECT pdlna FROM PdlNivelAtributo pdlna WHERE pdlna.denominacion=:denomi AND pdlna.idPdlNivel.idPdlNivel=:pdlNivel")
    public PdlNivelAtributo obtenerPorDenominacionYIdPdlNivel(@Param("denomi") String denominacion, @Param("pdlNivel") Long idPdlNivel);

}
