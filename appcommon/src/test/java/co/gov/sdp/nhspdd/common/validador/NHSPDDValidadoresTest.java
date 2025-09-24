package co.gov.sdp.nhspdd.common.validador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

public class NHSPDDValidadoresTest {

	private String prueba;
	private String pruebaEspacio;
	private String pruebaEspecial;
	private String doubleEnTexto;
	private Date fecha;
	private String horaAProbar;
	private String horaLargaAProbar;
	private String fechaAProbar;
	private String fechaLargaAProbar;
	private Double latitud;
	private String conversion;
	private String correo;

	@Before
	public void setUp() {
		prueba = "sumset";
		pruebaEspacio = "sumset sumset";
		pruebaEspecial = "sumset&";
		fecha = new Date();
		latitud = 0D;
		doubleEnTexto = "1";
		conversion = "";
		horaAProbar = "20:20";
		horaLargaAProbar = "20:20:20";
		fechaAProbar = "01/01/2020";
		fechaLargaAProbar = "2020-01-01 20:20:20";
		correo = "sumset@sumset.com";
	}

	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<NHSPDDValidadores> constructor = NHSPDDValidadores.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testEsValidoDouble() {
		assertFalse(NHSPDDValidadores.esValidoDouble(prueba));
		assertTrue(NHSPDDValidadores.esValidoDouble(doubleEnTexto));
	}

//	@Test
//	public void testEsValidoDate() {
//		assertFalse(NHSPDDValidadores.esValidoDate(prueba));
//		assertTrue(NHSPDDValidadores.esValidoDate(fechaAProbar));
//	}

	@Test
	public void testEsValidoDateTime() {
		assertFalse(NHSPDDValidadores.esValidoDateTime(prueba));
		assertTrue(NHSPDDValidadores.esValidoDateTime(fechaLargaAProbar));
	}

	@Test
	public void testEsValidoShortTime() {
		assertFalse(NHSPDDValidadores.esValidoShortTime(prueba));
		assertTrue(NHSPDDValidadores.esValidoShortTime(horaAProbar));
	}

	@Test
	public void testEsValidoTime() {
		assertFalse(NHSPDDValidadores.esValidoTime(prueba));
		assertTrue(NHSPDDValidadores.esValidoTime(horaLargaAProbar));
	}

	@Test
	public void testEsValidoEmail() {
		assertFalse(NHSPDDValidadores.esValidoEmail(prueba));
		assertTrue(NHSPDDValidadores.esValidoEmail(correo));
	}

	@Test
	public void testEsNumeroEntero() {
		assertFalse(NHSPDDValidadores.esNumeroEntero(prueba));
		assertTrue(NHSPDDValidadores.esNumeroEntero(doubleEnTexto));
	}

	@Test
	public void testEsTextoVacio() {
		assertFalse(NHSPDDValidadores.esTextoVacio(prueba));
		assertTrue(NHSPDDValidadores.esTextoVacio(""));
	}

	@Test
	public void testEsAlphaNumerico() {
		assertTrue(NHSPDDValidadores.esAlphaNumerico(prueba, false));
		assertTrue(NHSPDDValidadores.esAlphaNumerico(pruebaEspacio, true));
		assertFalse(NHSPDDValidadores.esAlphaNumerico(pruebaEspecial, false));
		assertFalse(NHSPDDValidadores.esAlphaNumerico(pruebaEspecial, true));
	}

	@Test
	public void testEsAlfabetico() {
		assertTrue(NHSPDDValidadores.esAlfabetico(prueba, false));
		assertTrue(NHSPDDValidadores.esAlfabetico(pruebaEspacio, true));
		assertFalse(NHSPDDValidadores.esAlfabetico(pruebaEspecial, false));
		assertFalse(NHSPDDValidadores.esAlfabetico(pruebaEspecial, true));
	}

	@Test
	public void testFormatearFecha() {
		assertNotNull(NHSPDDValidadores.formatearFecha(fecha));
	}

	@Test
	public void testEsTelefono() {
		assertNotNull(NHSPDDValidadores.esTelefono(prueba));
	}

	@Test
	public void testEsDireccion() {
		assertNotNull(NHSPDDValidadores.esDireccion(prueba));
	}

	@Test
	public void testEsCantidad() {
		assertNotNull(NHSPDDValidadores.esCantidad(prueba));
	}

	@Test
	public void testEsValidoLongitud() {
		assertTrue(NHSPDDValidadores.esValidoLongitud(prueba, 0, prueba.length()));
		assertFalse(NHSPDDValidadores.esValidoLongitud(prueba, 0, prueba.length() - 2));
		assertFalse(NHSPDDValidadores.esValidoLongitud(prueba, prueba.length() + 1, prueba.length() * 2));
	}

	@Test
	public void testEsLatitudValida() {
		assertTrue(NHSPDDValidadores.esLatitudValida(latitud));
		assertFalse(NHSPDDValidadores.esLatitudValida(-120D));
		assertFalse(NHSPDDValidadores.esLatitudValida(120D));
	}

	@Test
	public void testEsLongitudValida() {
		assertTrue(NHSPDDValidadores.esLongitudValida(latitud));
		assertFalse(NHSPDDValidadores.esLongitudValida(-190D));
		assertFalse(NHSPDDValidadores.esLongitudValida(190D));
	}

	@Test
	public void testEsNumeroCuenta() {
		assertNotNull(NHSPDDValidadores.esNumeroCuenta(prueba));
	}

	@Test
	public void testConvertirDateAString() {
		assertNotNull(NHSPDDValidadores.convertirDateAString(conversion, fecha));
	}

	@Test
	public void testConvertirCadenaAFecha() {
		assertNotNull(NHSPDDValidadores.convertirCadenaAFecha(NHSPDDConstantes.FORMAT_DATE_SHORT_WITH_SLASH_DMY, fechaAProbar));
		assertNull(NHSPDDValidadores.convertirCadenaAFecha(NHSPDDConstantes.FORMAT_DATE_SHORT_WITH_SLASH_DMY, "shdvc"));
	}
}
