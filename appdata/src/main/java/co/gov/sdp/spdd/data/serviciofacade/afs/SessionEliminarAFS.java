package co.gov.sdp.spdd.data.serviciofacade.afs;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author Sumset
 *
 */
@Component
public class SessionEliminarAFS extends SessionAFS implements Serializable {
	
	/**
	 * Atributo de serialización.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Método que permite construir un objeto de tipo
	 */
	public SessionEliminarAFS() {
		super();
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarComponenteGestionUsuario(Long id) {
		componenteGestionUsuarioServiceRepo.eliminar(id);
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarInformacionPresupuestal(Long id) {
		informacionPresupuestalServiceRepo.eliminar(id);
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarProyectoInversionUsuario(Long id) {
		proyectosInversionUsuarioServiceRepo.eliminar(id);
	}
	
	
}
