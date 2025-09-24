/**
 * 
 */
package co.gov.sdp.nhspdd.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.ColumnasDTO;
import co.gov.sdp.nhspdd.common.dto.ErroresPorCampoDTO;
import co.gov.sdp.nhspdd.common.dto.FilesDTO;
import co.gov.sdp.nhspdd.common.dto.TablasDTO;
import co.gov.sdp.nhspdd.common.validador.NHSPDDValidadores;

/**
 * @author Sumset
 *
 */
@Component
public class GestionarArchivos {

	/**
	 * 
	 * @param archivo
	 * @param delimiter
	 * @param isConfigFile
	 * @return
	 * @throws IOException
	 */
	public List<FilesDTO> leerArchivo(InputStream archivo, String delimiter) throws IOException {
		List<FilesDTO> result = new ArrayList<>();
		InputStreamReader isReader = new InputStreamReader(archivo);
		BufferedReader reader = new BufferedReader(isReader);
		String readLine = "";
		int numline = 1;

		while ((readLine = reader.readLine()) != null) {
			FilesDTO filesDTO = new FilesDTO();
			List<String> lstOutput = new ArrayList<>();

			for (String s : readLine.split(delimiter)) {
				lstOutput.add(s);
			}
			filesDTO.setLinea(lstOutput);
			filesDTO.setNumLinea(numline);
			filesDTO.setTieneError(false);
			filesDTO.setDescripcionErrorFila(NHSPDDConstantes.DESCRIPCION_NA);
			filesDTO.setErroresPorCampoDTO(new ArrayList<ErroresPorCampoDTO>());
			result.add(filesDTO);
			numline += 1;
		}
		reader.close();
		return result;
	}

	/**
	 * 
	 * @param xmlString
	 * @return
	 */
	public TablasDTO xmlStringToDTO(String xmlString) {
		TablasDTO result = new TablasDTO();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(TablasDTO.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = (TablasDTO) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		} catch (JAXBException e) {
			return null;
		}
		return result;
	}

	/**
	 * 
	 * @param lstFile
	 * @param lstColumnas
	 * @return
	 */
	public List<FilesDTO> comparaArchivosXML(List<FilesDTO> lstFile, List<ColumnasDTO> lstColumnas) {
		List<FilesDTO> result = new ArrayList<>();
		for (FilesDTO p : lstFile) {
			result.add(validarLineaXML(p, lstColumnas));
		}
		return result;
	}

	/**
	 * 
	 * @param filesDTO
	 * @param lstColumnas
	 * @return
	 */
	public FilesDTO validarLineaXML(FilesDTO filesDTO, List<ColumnasDTO> lstColumnas) {
		String str = "";
		StringBuilder erroresPorCampo;
		String erroresPorFila = "";
		HashMap<String, String> campos = new HashMap<>();

		ErroresPorCampoDTO erroresPorCampoDTO = new ErroresPorCampoDTO();
		List<ErroresPorCampoDTO> lstErroresPorCampoDTO = new ArrayList<>();
		boolean errors = false;

		if (filesDTO.getLinea().size() != lstColumnas.size()) {
			errors = true;

			erroresPorCampoDTO.setNombreCampo(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_TODOS_LOS_CAMPOS,
					PaqueteMensajeEnum.MENSAJES));
			erroresPorCampoDTO.setDescripcionError(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_LINEA_ESTRUCTURA_ARCHIVO_PLANO,
					PaqueteMensajeEnum.ERRORES));
			erroresPorCampoDTO.setTieneError(errors);
			lstErroresPorCampoDTO.add(erroresPorCampoDTO);
			erroresPorFila = armarErrorFila(lstErroresPorCampoDTO);
			campos.put(NHSPDDConstantes.CARGUE_ARCHIVO_CAMPOS,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_LINEA_ESTRUCTURA_ARCHIVO_PLANO,
							PaqueteMensajeEnum.ERRORES));
			filesDTO.setMapCampos(campos);
			filesDTO.setErroresPorCampoDTO(lstErroresPorCampoDTO);
			filesDTO.setTieneError(errors);
		} else {
			for (int num = 0; num < filesDTO.getLinea().size(); num++) {
				errors = false;
				erroresPorCampo = new StringBuilder(NHSPDDConstantes.DESCRIPCION_NA);
				erroresPorCampoDTO = new ErroresPorCampoDTO();
				str = filesDTO.getLinea().get(num);

				final int numero = num + 1;

				List<ColumnasDTO> columnasDTO = lstColumnas.stream()
						.filter(item -> item.getPosicion().intValue() == numero).collect(Collectors.toList());

				if (!columnasDTO.isEmpty()) {
					ColumnasDTO cnf = columnasDTO.get(0);
					erroresPorCampoDTO.setNombreCampo(cnf.getNombreCampo());
					erroresPorCampoDTO.setValorCampo(filesDTO.getLinea().get(num));
					erroresPorCampoDTO.setTieneError(errors);
					erroresPorCampoDTO.setTipoDato(cnf.getTipoDato());
					erroresPorCampoDTO.setEsLlaveForanea(cnf.getEsLlaveForanea());
					erroresPorCampoDTO.setEsValidacion(cnf.getEsValidacion());

					campos.put(cnf.getNombreCampo(), filesDTO.getLinea().get(num));

					boolean condicion1 = str.isEmpty();
					boolean condicion2 = str.trim().equals("");
					condicion1 = condicion1 || condicion2;

					if (!condicion1) {
						if (str.length() > cnf.getLongitud()) {
							errors = true;
							filesDTO.setTieneError(errors);
							erroresPorCampoDTO.setTieneError(errors);
							erroresPorCampo = new StringBuilder(NHSPDDMensajes.obtenerMensaje(
									NHSPDDConstantes.MENSAJE_VALIDACION_NO_COINCIDE_LONGITUD_CAMPO,
									PaqueteMensajeEnum.MENSAJES));
						}
						errors = false;
						if (cnf.getTipoDato().toUpperCase().trim().equals(NHSPDDConstantes.TIPO_DATO_CHAR)) {
							if (!NHSPDDValidadores.esAlphaNumerico(str, true)) {
								errors = true;
							}
						} else if (cnf.getTipoDato().toUpperCase().trim().equals(NHSPDDConstantes.TIPO_DATO_INTEGER)) {
							if (!NHSPDDValidadores.esNumeroEntero(str)) {
								errors = true;
							}
						} else {
							if (!NHSPDDValidadores.esValidoDouble(str)) {
								errors = true;
							}
						}
						if (errors) {
							filesDTO.setTieneError(errors);
							erroresPorCampoDTO.setTieneError(errors);
							erroresPorCampo.append(erroresPorCampo.toString() == ""
									? NHSPDDMensajes.obtenerMensaje(
											NHSPDDConstantes.MENSAJE_VALIDACION_NO_COINCIDE_EL_TIPO_DE_DATO,
											PaqueteMensajeEnum.MENSAJES)
									: NHSPDDConstantes.CARACTER_CONCATENAR + NHSPDDMensajes.obtenerMensaje(
											NHSPDDConstantes.MENSAJE_VALIDACION_NO_COINCIDE_EL_TIPO_DE_DATO,
											PaqueteMensajeEnum.MENSAJES));
						}

					} else {
						errors = true;
						filesDTO.setTieneError(errors);
						erroresPorCampoDTO.setTieneError(errors);
						erroresPorCampo = new StringBuilder(NHSPDDMensajes.obtenerMensaje(
								NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO, PaqueteMensajeEnum.ERRORES));
					}
					erroresPorCampoDTO.setDescripcionError(erroresPorCampo.toString());
					lstErroresPorCampoDTO.add(erroresPorCampoDTO);
					filesDTO.setMapCampos(campos);
					filesDTO.setErroresPorCampoDTO(lstErroresPorCampoDTO);
				}
			}
			erroresPorFila = armarErrorFila(lstErroresPorCampoDTO);
		}
		filesDTO.setDescripcionErrorFila(erroresPorFila);
		return filesDTO;
	}

	/**
	 * 
	 * @param lstErroresPorCampoDTO
	 * @return
	 */
	public String armarErrorFila(List<ErroresPorCampoDTO> lstErroresPorCampoDTO) {
		StringBuilder respuesta = new StringBuilder();
		if (lstErroresPorCampoDTO != null && !lstErroresPorCampoDTO.isEmpty()) {
			for (int i = 0; i < lstErroresPorCampoDTO.size(); i++) {
				if (!lstErroresPorCampoDTO.get(i).getDescripcionError().equals("NA")) {
					if (i == lstErroresPorCampoDTO.size() - 1) {
						respuesta.append(lstErroresPorCampoDTO.get(i).getNombreCampo()).append(":")
								.append(lstErroresPorCampoDTO.get(i).getDescripcionError());
					} else {
						respuesta.append(lstErroresPorCampoDTO.get(i).getNombreCampo()).append(":")
								.append(lstErroresPorCampoDTO.get(i).getDescripcionError())
								.append(NHSPDDConstantes.CARACTER_SPLIT_PUNTOYCOMA);
					}
				}
			}
		}
		return respuesta.toString();
	}
}