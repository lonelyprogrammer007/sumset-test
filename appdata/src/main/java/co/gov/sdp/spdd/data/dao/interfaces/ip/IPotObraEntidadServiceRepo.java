package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPotObraEntidadServiceRepo extends IOperacionesBasicasFacade<PotObraEntidad, Long>, Serializable {
	
	/**
	 * Metodo que pemite obtener todos los PotObraEntidad por medio del potObra
	 * @param idPotObra Long que representa el identificador del PotObra por el cual se va a buscar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public List<PotObraEntidad> obtenerTodosPorIdPotObra(Long idPotObra);
	
	/**
	 * Metodo que permite eliminar todos los PotObraEntidad que se pasan en la lista
	 * @param listaPotObraEntidad Lista de PotObraEntidad para eliminar
	 */
	public void eliminarTodos(List<PotObraEntidad> listaPotObraEntidad);
	
	/**
	 * Metodo que permite obtener el PotObraEntidad correspondiente a la entidad y PotObra indicados
	 * @param codigoEntidad String que representa el codigo de la entidad
	 * @param idPotObra Long que representa el identificador de la potObra
	 * @return un objeto de tipo PotObraEntidad con la informacion correspondiente
	 */
	public PotObraEntidad obtenerPotCodigoEntidadYIdPotObra(String codigoEntidad, Long idPotObra);
	
}
