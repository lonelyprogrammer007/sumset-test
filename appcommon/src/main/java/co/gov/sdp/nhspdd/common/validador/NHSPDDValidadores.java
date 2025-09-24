package co.gov.sdp.nhspdd.common.validador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.TimeValidator;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

/**
 * Clase para el manejo de validadores generales
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/10/2019
 *
 */
public class NHSPDDValidadores {

	private static DateValidator dateValidador = DateValidator.getInstance();
	private static DoubleValidator doubleValidador = DoubleValidator.getInstance();
	private static EmailValidator emailValidador = EmailValidator.getInstance();
	private static IntegerValidator integerValidador = IntegerValidator.getInstance();
	private static TimeValidator timeValidador = TimeValidator.getInstance();

	private NHSPDDValidadores() {
		super();
	}

	/**
	 * Metodo que valida si una cadena es un Double valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un double valido, <code>false</code> en caso
	 *         contrario
	 */
	public static boolean esValidoDouble(String pValue) {
		return doubleValidador.isValid(pValue);
	}

	/**
	 * Metodo que valida si una cadena es un Date valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un Date valido, <code>false</code> en caso
	 *         contrario
	 */
	public static boolean esValidoDate(String pValue) {
		return dateValidador.validate(pValue, NHSPDDConstantes.FORMAT_DATE_SHORT_WITH_SLASH) != null;
	}

	/**
	 * Metodo que valida si una cadena es un DateTime valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un DateTime valido, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esValidoDateTime(String pValue) {
		return dateValidador.validate(pValue, NHSPDDConstantes.FORMAT_DATETIME_SHORT_WITH_DASH) != null;
	}

	/**
	 * Metodo que valida si una cadena es un ShortTime valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un ShortTime valido, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esValidoShortTime(String pValue) {
		return timeValidador.validate(pValue, NHSPDDConstantes.FORMAT_SHORT_TIME) != null;
	}

	/**
	 * Metodo que valida si una cadena es un Time valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un Time valido, <code>false</code> en caso
	 *         contrario
	 */
	public static boolean esValidoTime(String pValue) {
		return timeValidador.validate(pValue, NHSPDDConstantes.FORMAT_TIME) != null;
	}

	/**
	 * Metodo que valida si una cadena es un Email valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un Email valido, <code>false</code> en caso
	 *         contrario
	 */
	public static boolean esValidoEmail(String pValue) {
		return emailValidador.isValid(pValue);
	}

	/**
	 * Metodo que valida si una cadena es un Numero Entero valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un Numero Entero valido, <code>false</code>
	 *         en caso contrario
	 */
	public static boolean esNumeroEntero(final String pValue) {
		return integerValidador.isValid(pValue);
	}

	/**
	 * Metodo que valida si una cadena es un Texto Vacio valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un Texto Vacio valido, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esTextoVacio(final Object pValue) {
		if (pValue == null) {
			return true;
		}
		String valueTrim = pValue.toString().trim();
		if ((valueTrim).equals("")) {
			return true;
		}
		return (valueTrim).equals("null");
	}

	/**
	 * Metodo que valida si una cadena contiene solo numeros y letras
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si contiene solo numeros y letras,
	 *         <code>false</code> en caso contrario
	 */
	public static boolean esAlphaNumerico(final String pValue, final boolean pSpaces) {
		Pattern pat;
		if (pSpaces) {
			pat = Pattern.compile("[A-Za-z0-9 áéíóúÁÉÍÓÚÑñ]+");
		} else {
			pat = Pattern.compile("[A-Za-z0-9áéíóúÁÉÍÓÚÑñ]+");
		}
		final Matcher mat = pat.matcher(pValue);
		return mat.matches();
	}

	/**
	 * Metodo que valida si una cadena contiene letras
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si contiene letras, <code>false</code> en caso
	 *         contrario
	 */
	public static boolean esAlfabetico(final String pValue, final boolean pSpaces) {
		Pattern pat;
		if (pSpaces) {
			pat = Pattern.compile("[A-Za-z áéíóúAÉÍÓÚÑñ]+");
		} else {
			pat = Pattern.compile("[A-Za-záéíóúAÉÍÓÚÑñ]+");
		}
		Matcher matcher = pat.matcher(pValue);
		return matcher.matches();
	}

	/**
	 * Metodo que formatea una fecha y devuelve la cadena con el formato designado
	 *
	 * @param pValue Cadena a comparar
	 * @return La cadena aplicando el formato dd/MM/yyyy
	 */
	public static String formatearFecha(Date pDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(pDate);
	}

	/**
	 * Metodo que valida si una cadena es un telefono
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un telefono valido, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esTelefono(final String pValue) {
		Pattern pat = Pattern.compile("[()0-9- .*# (Ext) (ext)]+");
		final Matcher matcher = pat.matcher(pValue);
		return matcher.matches();
	}

	/**
	 * Metodo que valida si una cadena es una direccion valida
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es una direccion valida, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esDireccion(final String pValue) {
		Pattern pat = Pattern.compile("[()A-Za-z0-9- áéíóúÁÉÍÓÚÑñ.*#/]+");
		final Matcher matcher = pat.matcher(pValue);
		return matcher.matches();
	}

	/**
	 * Metodo que valida si una cadena es una cantidad valida
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es una cantidad valida, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esCantidad(final String pValue) {
		Pattern pat;
		pat = Pattern.compile("[0-9]{1,20}");
		final Matcher mat = pat.matcher(pValue);
		return mat.matches();
	}

	/**
	 * Metodo que valida si una cadena tiene la longitud establecida
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si tiene la longitud establecida,
	 *         <code>false</code> en caso contrario
	 */
	public static boolean esValidoLongitud(final String pValue, final int minlength, final int maxlength) {
		return (pValue.length() >= minlength && pValue.length() <= maxlength);
	}

	/**
	 * Metodo que valida si una cadena es una latitud valida
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es una latitud valida, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esLatitudValida(final Double pValue) {
		return !(pValue < (-90) || pValue > 90);
	}

	/**
	 * Metodo que valida si una cadena es una longitud valida
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es una longitud valida, <code>false</code> en
	 *         caso contrario
	 */
	public static boolean esLongitudValida(final Double pValue) {
		return !(pValue < (-180) || pValue > 180);
	}

	/**
	 * Metodo que valida si una cadena es un numero de cuenta valido
	 *
	 * @param pValue Cadena a comparar
	 * @return <code>true</code> si es un numero de cuenta valido,
	 *         <code>false</code> en caso contrario
	 */
	public static boolean esNumeroCuenta(final String pValue) {
		Pattern pat;
		pat = Pattern.compile("[0-9]{1,20}");
		final Matcher mat = pat.matcher(pValue);
		return mat.matches();
	}

	/**
	 * Metodo que convierte una objeto de tipo Date en uno de tipo String
	 *
	 * @param pValue Cadena a comparar
	 * @return Representacion del Date en tipo String
	 */
	public static String convertirDateAString(String format, Date date) {
		DateFormat dDateStart = new SimpleDateFormat(format);
		return dDateStart.format(date);
	}

	/**
	 * Metodo que convierte una objeto de tipo String en uno de tipo Date
	 *
	 * @param pValue Cadena a comparar
	 * @return Representacion del String en tipo Date
	 */
	public static Date convertirCadenaAFecha(String format, String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
