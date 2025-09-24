package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PotObra;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IPddNivelAtributoRepo extends PagingAndSortingRepository<PddNivelAtributo, Long>, Serializable {

	
	/**
	 * 
	 * @return
	 */
    @Query("SELECT n from PddNivelAtributo n WHERE n.idAtributo NOT IN(SELECT c.idComponenteGestion FROM ComponenteGestionUsuario c) and n.proyectoEstrategico=1")
    public List<PddNivelAtributo> pddNivelAtributoObtenerLibres();
    
    /**
     * Metodo que permite obetner un PddNivelAtributo por medio del numeoro y del identificador del nivel al que esta relacionado
     * @param numero string que representa el numero del nivel atributo
     * @param idPddNivel Long que reprsenta el identificador del pddNivel al que esta asociado el atributo nivel
     * @return un objeto de tipo PddNivelAtributo con la informacion correspondiente.
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE LOWER(pddna.numero)= LOWER(:numero) AND pddna.idPddNivel.idPddNivel=:pddNivel AND pddna.idAtributoPadre.idAtributo=:atributoPadre")
    public PddNivelAtributo obtenerPorNumeroYIdPddNivel(@Param("numero") Long numero,@Param("pddNivel") Long idPddNivel, @Param("atributoPadre") Long idAtributoPadre);
    
    /**
     * Metodo que permite obetner un PddNivelAtributo por medio de la denominacion y del identificador del nivel al que esta relacionado
     * @param numero string que representa el numero del nivel atributo
     * @param idPddNivel Long que reprsenta el identificador del pddNivel al que esta asociado el atributo nivel
     * @return un objeto de tipo PddNivelAtributo con la informacion correspondiente.
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE LOWER(pddna.denominacion)= LOWER(:denominacion) AND pddna.idPddNivel.idPddNivel=:pddNivel")
    public PddNivelAtributo obtenerPorDenominacionYIdPddNivel(@Param("denominacion") String denominacion,@Param("pddNivel") Long idPddNivel);
    
    /**
     * Permite obtener todos los PddNivelAtributo de un nivel en especifico en orden ascendente y paginados
     * @param idPddNivel Long que representa el identificador del nivel que se quieren obtener los atributos
     * @return una lista de tipo PddNivelAtributo
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.idPddNivel.idPddNivel=:pddNivel")
    public Page<PddNivelAtributo> obtenerTodosPorIdPddNivelPaginado(@Param("pddNivel") Long idPddNivel, Pageable paginador);
    
    /**
     * Permite obtener todos los PddNivelAtributo de un nivel en especifico en orden ascendente
     * @param idPddNivel
     * @return
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.idPddNivel.idPddNivel=:pddNivel ORDER BY pddna.numero ASC")
    public List<PddNivelAtributo> obtenerTodosPorIdPddNivel(@Param("pddNivel") Long idPddNivel);
    
    /**
     * Permite obtener todos los PddNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return una lista de tipo PddNivelAtributo
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.idAtributoPadre.idAtributo=:atributoPadre")
    public Page<PddNivelAtributo> obtenerTodosPorIAtributoPadrePaginado(@Param("atributoPadre") Long idAtributoPadre, Pageable paginador);
    
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.idAtributoPadre.idAtributo=:atributoPadre ORDER BY pddna.numero ASC")
    public List<PddNivelAtributo> obtenerTodosPorIAtributoPadre(@Param("atributoPadre") Long idAtributoPadre);
    
    /**
     * Permite obtener todos los PddNivelAtributo que esten desbalanceados, es decir que el campo sumPond sea diferente de 100
     * @return una lista de tipo PddNivelAtributo
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.sumPond <> 100 AND pddna.idPddNivel.idPlanDesarrollo.idPlanDesarrollo = :pdd")
    public List<PddNivelAtributo> obtenerTodosDesbalanceados(@Param("pdd") Long idPlanDesarrollo);
    
    /**
     * Permite obtener un PddNivelAtributo por el numero del PddNivelAtributo del codigo de nivel 1 del PddNivel
     * @param numero Long que respresenta el numero del pddNivelAtributo de primer nivel que se quiere buscar
     * @return un objeto PddNivelAtributo con la informacion correspondiente
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.numero= :numero AND pddna.idPddNivel.idPlanDesarrollo.idPlanDesarrollo = :pdd AND pddna.idPddNivel.codNivel=1 ")
    public PddNivelAtributo obtenerPorNumeroDePrimerNivelDeIdPlanDesarrollo(@Param("numero") Long numero, @Param("pdd") Long idPlanDesarrollo);
    
    /**
     * Metodo que permite obtener un PddNivelAtributo por el numero del PddNivelAtributo, el codigo_numero del PddNivel y el identificador del pddnivelAtributo
     * @param numero Long que respresenta el numero del pddNivelAtributo de primer nivel que se quiere buscar
     * @param codigoNumero Long que represnta el codigoNumero del PddNivel al que esta asociado el PddNivelAtributo
     * @param idAtributoPadre Long que representa el identificador del atributopadre al que esta relacionado
     * @return un objeto PddNivelAtributo con la informacion correspondiente
     */
    @Query("SELECT pddna FROM PddNivelAtributo pddna WHERE pddna.numero= :numero AND pddna.idPddNivel.codNivel=:codigoNum AND pddna.idAtributoPadre.idAtributo=:atributoPadre")
    public PddNivelAtributo obtenerPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(@Param("numero") Long numero, @Param("codigoNum") Long codigoNumero, @Param("atributoPadre") Long idAtributoPadre);
    
    /**
     * Permite obtener la ponderacion total de todos los PddNivelAtributo de un nivel en especifico
     * @param idPddNivel Long que representa el identificador del nivel que se quieren obtener los atributos
     * @return un long que corresponde a la suma de las ponderaciones de todos los PddNivelAtributo
     */
    @Query("SELECT SUM(pddna.ponderacion) FROM PddNivelAtributo pddna WHERE pddna.idPddNivel.idPddNivel=:pddNivel")
    public Long obtenerPonderacionTotalDeTodosPorIdPddNivel(@Param("pddNivel") Long idPddNivel);
    
    /**
     * Permite obtener la ponderacion total de todos los PddNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return Long que corresponde a la suma de las ponderaciones de todos los PddNivelAtributo
     */
    @Query("SELECT SUM(pddna.ponderacion) FROM PddNivelAtributo pddna WHERE pddna.idAtributoPadre.idAtributo=:atributoPadre")
    public Long obtenerPonderacionTotalDeTodosPorIAtributoPadre(@Param("atributoPadre") Long idAtributoPadre);
    
}
