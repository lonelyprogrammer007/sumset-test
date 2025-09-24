package co.gov.sdp.spdd.data.serviciofacade.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

/**
 * Clase que representa el Facade para el servicio Eliminar del modulo IP
 * 
 * @author DANIEL, Bryan Munoz
 * @version 1.0 24/01/2020
 */
@Component
public class SessionEliminarIP extends SessionIP implements Serializable {

	/**
	 * 
	 * @param id
	 */
	public void eliminarPddMeta(Long id) {
		pddMetaServiceRepo.eliminar(id);
	}

	/**
	 * 
	 * @param id
	 */
	public void eliminarPddObraConcreta(Long id) {
		pddObraConcretaServiceRepo.eliminar(id);

	}

	/**
	 * Metodo que permite eliminar un pddCompromisoEspecifico por medio de su
	 * identificador
	 * 
	 * @param idPddCompromisoEspecifico Long que representa el identificador del
	 *                                  pddCompromisoEspecifico que se desea
	 *                                  eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPddCompromisoEspecifico(Long idPddCompromisoEspecifico) throws SpddExceptions {
		pddCompromisoEspecificoServiceRepo.eliminar(idPddCompromisoEspecifico);
	}

	/**
	 * 
	 * @param idPoblacion
	 */
	public void eliminarPrbPoblacion(Long idPoblacion) throws SpddExceptions {

		poblacionServiceRepo.eliminar(idPoblacion);
	}

	/**
	 * 
	 * @param idProbInd
	 */
	public void eliminarPrbIndicador(Long idProbInd) {
		pddPrbIndicadorServiceRepo.eliminar(idProbInd);
	}

	/**
	 * 
	 * @param idMetaResultado
	 */
	public void eliminarPddMetaResultado(Long idMetaResultado) {
		pddMetaResultadoServiceRepo.eliminar(idMetaResultado);
	}

	/**
	 * 
	 * @param id
	 */
	public void eliminarIndicadorMetaResultado(Long id) {
		pddMRIndicadorServiceRepo.eliminar(id);
	}

	/**
	 * 
	 * @param idMetaProducto
	 */
	public void eliminarMetaProducto(Long idMetaProducto) {
		pddMetaProductoServiceRepo.eliminar(idMetaProducto);
	}

	/**
	 * 
	 * @param idRamaPot
	 */
	public void eliminarRamaPot(Long idRamaPot) {
		potRamaServiceRepo.eliminar(idRamaPot);
	}

	/**
	 * 
	 * @param idNivelPot
	 */
	public void eliminarNivelPot(Long idNivelPot) {
		potNivelServiceRepo.eliminar(idNivelPot);
	}

	/**
	 * 
	 * @param indicadorMetaProducto
	 */
	public void eliminarIndicadorMetaProducto(Long indicadorMetaProducto) {
		pddMpIndicadorServiceRepo.eliminar(indicadorMetaProducto);
	}

	/**
	 * Metodo que permite eliminar un PddNivelAtriguto
	 * 
	 * @param idAtributo Long que representa el identificador PddNivelAtriguto que
	 *                   se quiere eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPddNivelAtributo(Long idAtributo) throws SpddExceptions {
		pddNivelAtributoServiceRepo.eliminar(idAtributo);
	}

	/**
	 * Metodo que permite eliminar una PotObra
	 * 
	 * @param idPotObra Long que presenta el identificador de la PotObra que se
	 *                  desea eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPotObra(Long idPotObra) throws SpddExceptions {
		potObraServiceRepo.eliminar(idPotObra);
	}

	/**
	 * Metodo que permite eliminar todos los PotObraEntidad que esten relacionados a
	 * un PotObra
	 * 
	 * @param idPotObra Long que representa el identificador del PotObra al que se
	 *                  le quiere eliminar todas las PotObraEntidad relacionadas
	 */
	public void eliminarTodosPotObraEntidadPorIdPotObra(Long idPotObra) throws SpddExceptions {
		List<PotObraEntidad> listaPotObraEntidad = potObraEntidadServiceRepo.obtenerTodosPorIdPotObra(idPotObra);

		if (!listaPotObraEntidad.isEmpty()) {
			potObraEntidadServiceRepo.eliminarTodos(listaPotObraEntidad);
		}

	}

	/**
	 * Metodo que permite eliminar un potInstrumento
	 * 
	 * @param idPotInstrumento Long que representa el identificador del
	 *                         potInstrumento a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPotInstrumento(Long idPotInstrumento) throws SpddExceptions {
		potInstrumentoServiceRepo.eliminar(idPotInstrumento);
	}

	/**
	 * 
	 * @param idMpEntidad
	 * @throws SpddExceptions
	 */
	public void eliminarMpEntidad(Long idMpEntidad) throws SpddExceptions {
		pddMpEntidadServiceRepo.eliminar(idMpEntidad);
	}

	/**
	 * Metodo que permite eliminar un indicador entidad meta producto
	 * @param idIndProd
	 * @throws SpddExceptions
	 */
	public void eliminarMpIndicadorEntidadProducto(Long idIndProd) throws SpddExceptions {
		pddMpIndicadorEntidadServiceRepo.eliminar(idIndProd);
	}

	/**
	 * 
	 * @param idRango
	 */
	public void eliminarPddRangoPonderacion(Long idRango) throws SpddExceptions {
		pddRangoPonderacionServiceRepo.eliminar(idRango);

	}
}
