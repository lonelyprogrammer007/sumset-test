package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvTipo que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
public interface IBpProyInvTipoServiceRepo extends IOperacionesBasicasFacade<BpProyInvTipo, Long>, Serializable  {
	
	/**
	 * Metodo que permite obtener una lista de BpProyInvTipo relacionado a un proyecto de inversion
	 * @param idProyecto Long que representa el identificador por le cual se va a buscar los BpProyInvTipo 
	 * @return una lista BpProyInvTipo con la informacion correspondiente
	 */
	public List<BpProyInvTipo> obtenerPorIdProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite obtener un objeto de  tipo BpProyInvTipo correspondiente al idLsTipo e idProyInversion pasados como parametros
	 * @param idLsTipo Long que representa el identificador del argumento de lista simple que reprsenta el tipo.
	 * @param idProyInversion Long que representa el identificador del proyecto de inversion
	 * @return una lista BpProyInvTipo con la informacion correspondiente
	 */
	public BpProyInvTipo obtenerPorIdLsTipoYIdProyInversion(Long idLsTipo, Long idProyInversion);
	
	/**
	 * Metodo que permite eliminar de la BD todos los BpProyInvTipo que van en la lista pasada como parametro
	 * @param ListaBpProyInvTipo Lista de tipo BpProyInvTipo con los objetos a eliminar de la BD
	 */
	public void eliminar(List<BpProyInvTipo> listaBpProyInvTipo);

}
