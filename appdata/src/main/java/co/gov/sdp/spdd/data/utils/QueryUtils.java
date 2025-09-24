package co.gov.sdp.spdd.data.utils;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.validador.NHSPDDValidadores;

public class QueryUtils {

	public static <T> Predicate filtroFechas(String campo, String fecha, String formato, CriteriaBuilder cb,
			Root<T> root) {

		Date actual = NHSPDDValidadores.convertirCadenaAFecha(formato, fecha + NHSPDDConstantes.FECHA_HORA_INICIAL);

		Date finalFecha = NHSPDDValidadores.convertirCadenaAFecha(formato, fecha + NHSPDDConstantes.FECHA_HORA_FINAL);

		return cb.between(root.get(campo), actual, finalFecha);
	}
	

}
