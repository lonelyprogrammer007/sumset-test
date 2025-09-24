package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class EjemploDTO {

	private String codigoEntidad;

	public EjemploDTO(Object[] lista) {
		if (lista != null) {
			if (lista.length >= 1) {
				System.out.println(lista[0] instanceof String);
//				codigoEntidad = lista[0]EjemploDTO;
			}
		}
	}
}
