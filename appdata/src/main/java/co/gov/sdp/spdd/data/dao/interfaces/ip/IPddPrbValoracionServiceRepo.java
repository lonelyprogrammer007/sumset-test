package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a una valoracion de una problematica que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
public interface IPddPrbValoracionServiceRepo extends IOperacionesBasicasFacade<PddPrbValoracion, Long>, Serializable {
	
	/**
	 * Metodo que permite bucar de una PddPrbValoracion por medio del identificador de la problematica y el momento
	 * @param idProblematica Long que representa el identificador de la problematica por la cual se va a buscar o filtrar
	 * @param momento Long que representa el valor del momento (1-antes, 2-Durante, 3-Despues)
	 * @return un objeto de tipo PddPrbValoracion con la iformacion correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracion obtenerPorIdProblematicaYMomento(Long idProblematica, Long momento) throws SpddExceptions;

	
	
}
