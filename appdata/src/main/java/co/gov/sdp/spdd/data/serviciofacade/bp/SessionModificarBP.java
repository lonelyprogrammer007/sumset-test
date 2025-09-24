package co.gov.sdp.spdd.data.serviciofacade.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
<<<<<<< HEAD
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
=======
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
>>>>>>> developer

/**
 * Clase que representa el Facade para el servicio Modificar del modulo BP
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Component
public class SessionModificarBP extends SessionBP implements Serializable {

<<<<<<< HEAD
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) {

		return bpProyInvPoblacionMapper.bpProyInvPoblacionEntityToDTO(bpProyInvPoblacionServiceRepo
				.guardar(bpProyInvPoblacionMapper.bpProyInvPoblacionDTOToEntity(peticion)));

=======
	/**
	 * Metodo que permite modificar un BpProyInvAnualiza
	 * 
	 * @param peticion el BpProyInvAnualizaDTO a modificar
	 * @return un objeto de tipo BpProyInvFinanciaDTO con el objeto modificado
	 */
	public BpProyInvFinanciaDTO modificarTodosProyInvAnualizaDelProyInvFinancia(BpProyInvFinanciaDTO peticion) {

		List<BpProyInvAnualiza> listAnualiza = bpProyInvAnualizaServiceRepo
				.obtenerPorIdTodosProyInvAnualizacion(peticion.getIdFuente());

		BpProyInvFinanciaDTO respuesta = peticion;

		if (!listAnualiza.isEmpty()) {

			for (BpProyInvAnualiza anualiza : listAnualiza) {

				if (anualiza.getVigencia().equals(peticion.getVigencia1())) {

					anualiza.setMonto(peticion.getMontoAnio1());
				}
				if (anualiza.getVigencia().equals(peticion.getVigencia2())) {

					anualiza.setMonto(peticion.getMontoAnio2());
				}
				if (anualiza.getVigencia().equals(peticion.getVigencia3())) {

					anualiza.setMonto(peticion.getMontoAnio3());
				}
				if (anualiza.getVigencia().equals(peticion.getVigencia4())) {

					anualiza.setMonto(peticion.getMontoAnio4());
				}
				if (anualiza.getVigencia().equals(peticion.getVigencia5())) {

					anualiza.setMonto(peticion.getMontoAnio5());
				}

				bpProyInvAnualizaServiceRepo.guardar(anualiza);

			}
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROY_INV_ANUALIZA,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		}
		return respuesta;
>>>>>>> developer
	}

}
