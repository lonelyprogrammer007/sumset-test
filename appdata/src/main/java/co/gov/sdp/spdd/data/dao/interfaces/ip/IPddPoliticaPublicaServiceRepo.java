package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.spdd.data.model.ip.PddPoliticaPublica;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a PddPoliticaPublica que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 06/06/2020
 */
public interface IPddPoliticaPublicaServiceRepo extends IOperacionesBasicasFacade<PddPoliticaPublica, Long>, Serializable {
	
	/**
	 * Metodo que permite obtenre todos las policitcas publicas filtradas por sus campos
	 * @param pddPoliticaPublicadDTO objeto de tipo PddPoliticaPublicaDTO que contiene la informacion para filtar
	 * @param inicio
	 * @param limite
	 * @return
	 */
	public FiltroDTO obtenerTodosFiltrado(PddPoliticaPublicaDTO pddPoliticaPublicadDTO, Long inicio, Integer limite);

	/**
	 * Metodo que permite consultar las politicas publicas que no estan relacionadas con el proyecto de inversion
	 * @param idProyInversion Long que representa el proyecto de inversion que esta relacionado con las politicas publicas
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	public List<PddPoliticaPublica> obtenerTodosSinRelacionConProyectoInversion(Long idProyInversion);
	
	/**
	 * Metodo que permite consultar todas las politicas publicas ordenadas por el campo Politca ascendentemente
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	public List<PddPoliticaPublica> obtenerTodosOrdenadosPorNombrePolitica();

}
