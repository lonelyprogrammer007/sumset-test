package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPdlNivelAtributoServiceRepo extends IOperacionesBasicasFacade<PdlNivelAtributo, Long> {
	
    /**
     * Permite obtener todos los PdlNivelAtributo de un nivel en especifico en orden ascendente
     * @param idPdlNivelAtributo Long que representa el identificador del nivel que se quieren obtener los atributos
     * @return una lista de tipo PdlNivelAtributo
     */
    public Page<PdlNivelAtributo> obtenerTodosPorIdPdlNivelAtributoPaginado(Long idPdlNivel, Pageable paginador);
    
    /**
     * Permite obtener todos los PdlNivelAtributo que corresponden al nivel atributo padre
     * @param idAtributoPadre Long que representa el identificador del nivel atributo padre por el cual se va a buscar
     * @return una lista de tipo PdlNivelAtributo
     */
    public Page<PdlNivelAtributo> obtenerTodosPorIdAtributoPadrePaginado(Long idAtributoPadre, Pageable paginador);

    /**
     * Metodo que permite obetner un PdlNivelAtributo por medio de la denominacion y del identificador del nivel al que esta relacionado
     * @param String que representa la denominacion del nivel atributo
     * @param idPdlNivel Long que representa el identificador del pdlNivel al que esta asociado el atributo nivel
     * @return un objeto de tipo PdlNivelAtributo con la informacion correspondiente.
     */
	public PdlNivelAtributo obtenerPorDenominacionYIdPdlNivel(String denominacion, Long idPdlNivel);
}
