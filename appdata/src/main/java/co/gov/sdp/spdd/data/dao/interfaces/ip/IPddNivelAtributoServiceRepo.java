package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPddNivelAtributoServiceRepo extends IOperacionesBasicasFacade<PddNivelAtributo, Long> {
	
	/**
	 * 
	 * @return
	 */
	public List<PddNivelAtributo> pddNivelAtributoObtenerLibres();
	
	 /**
     * Metodo que permite obetner un PddNivelAtributo por medio del numeoro y del identificador del nivel al que esta relacionado
     * @param numero string que representa el numero del nivel atributo
     * @param idPddNivel Long que reprsenta el identificador del pddNivel al que esta asociado el atributo nivel
     * @return un objeto de tipo PddNivelAtributo con la informacion correspondiente.
     */
    public PddNivelAtributo obtenerPorNumeroYIdPddNivel(Long numero, Long idPddNivel, Long idAtributoPadre);
    
    /**
     * Permite obtener todos los PddNivelAtributo de un nivel en especifico en orden ascendente y paginado
     * @param idPddNivel Long que representa el identificador del nivel que se quieren obtener los atributos
     * @return una lista de tipo PddNivelAtributo
     */
    public Page<PddNivelAtributo> obtenerTodosPorIdPddNivelPaginado(Long idPddNivel, Pageable paginador);
    
    /**
     * Permite obtener todos los PddNivelAtributo de un nivel en especifico en orden ascendente no paginado
     * @param idPddNivel Long que representa el identificador del nivel que se quieren obtener los atributos
     * @return una lista de tipo PddNivelAtributo
     */
    public List<PddNivelAtributo> obtenerTodosPorIdPddNivel(Long idPddNivel);
    
    /**
     * Metodo que permite obetner un PddNivelAtributo por medio de la denominacion y del identificador del nivel al que esta relacionado
     * @param numero string que representa el numero del nivel atributo
     * @param idPddNivel Long que reprsenta el identificador del pddNivel al que esta asociado el atributo nivel
     * @return un objeto de tipo PddNivelAtributo con la informacion correspondiente.
     */
    public PddNivelAtributo obtenerPorDenominacionYIdPddNivel(String denominacion, Long idPddNivel);
    
    /**
     * Permite obtener todos los PddNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return una lista de tipo PddNivelAtributo
     */
    public Page<PddNivelAtributo> obtenerTodosPorIAtributoPadrePaginado(Long idAtributoPadre, Pageable paginador);
    
    /**
     * Permite obtener todos los PddNivelAtributo que esten desbalanceados, es decir que el campo sumPond sea diferente de 100
     * @return una lista de tipo PddNivelAtributo
     */
    public List<PddNivelAtributo> obtenerTodosDesbalanceados(Long idPlanDesarrollo);
    
    /**
     * Permite obtener todos los PddNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return una lista de tipo PddNivelAtributo
     */
    public List<PddNivelAtributo> obtenerTodosPorIAtributoPadre(Long idAtributoPadre);
    
    /**
     * Permite obtener un PddNivelAtributo por el numero del PddNivelAtributo del codigo de nivel 1 del PddNivel
     * @param numero Long que respresenta el numero del pddNivelAtributo de primer nivel que se quiere buscar
     * @return un objeto PddNivelAtributo con la informacion correspondiente
     */
    public PddNivelAtributo obtenerPorNumeroDePrimerNivelDeIdPlanDesarrollo(Long numero, Long idPlanDesarrollo);
    
    /**
     * Metodo que permite obtener un PddNivelAtributo por el numero del PddNivelAtributo, el codigo_numero del PddNivel y el identificador del pddnivelAtributo
     * @param numero Long que respresenta el numero del pddNivelAtributo de primer nivel que se quiere buscar
     * @param codigoNumero Long que represnta el codigoNumero del PddNivel al que esta asociado el PddNivelAtributo
     * @param idAtributoPadre Long que representa el identificador del atributopadre al que esta relacionado
     * @return un objeto PddNivelAtributo con la informacion correspondiente
     */
    public PddNivelAtributo obtenerPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre( Long numero, Long codigoNumero, Long idAtributoPadre);

    /**
     * Permite obtener la ponderacion total de todos los PddNivelAtributo de un nivel en especifico
     * @param idPddNivel Long que representa el identificador del nivel que se quieren obtener los atributos
     * @return una lista de tipo PddNivelAtributo
     */
    public Long obtenerPonderacionTotalDeTodosPorIdPddNivel(Long idPddNivel);
    
    /**
     * Permite obtener la ponderacion total de todos los PddNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return Long que corresponde a la suma de las ponderaciones de todos los PddNivelAtributo
     */
    public Long obtenerPonderacionTotalDeTodosPorIAtributoPadre(Long idAtributoPadre);



}
