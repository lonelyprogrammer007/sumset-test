package co.gov.sdp.nhspdd.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

public class NHSPDDMensajesTest {

	private String constante, constanteFunciona, lenguaje;
	private PaqueteMensajeEnum tipo;

	@Before
	public void setUp() {
		constante = "";
		constanteFunciona = NHSPDDConstantes.MENSAJE_ARCHIVO_PROCESADO_NO_ENCONTRADO;
		lenguaje = "es";
		tipo = PaqueteMensajeEnum.MENSAJES;
	}

	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<NHSPDDMensajes> constructor = NHSPDDMensajes.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testObtenerMensajeStringString() {
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constante, lenguaje));
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constanteFunciona, lenguaje));
		assertEquals("No se encontro el mensaje seleccionado: ", NHSPDDMensajes.obtenerMensaje(constante, lenguaje));
	}

	@Test
	public void testObtenerMensajeStringNull() {
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constante, (String) null));
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constanteFunciona, (String) null));
		assertEquals("No se encontro el mensaje seleccionado: ", NHSPDDMensajes.obtenerMensaje(constante, (String) null));
	}

	@Test
	public void testObtenerMensajeStringPaqueteMensajeEnum() {
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constante, tipo));
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constanteFunciona, tipo));
		assertEquals("No se encontro el mensaje seleccionado: ", NHSPDDMensajes.obtenerMensaje(constante, tipo));
	}

	@Test
	public void testObtenerMensajeStringPaqueteNull() {
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constante, (PaqueteMensajeEnum) null));
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constanteFunciona, (PaqueteMensajeEnum) null));
		assertEquals("No se encontro el mensaje seleccionado: ", NHSPDDMensajes.obtenerMensaje(constante, (PaqueteMensajeEnum) null));
	}

	@Test
	public void testObtenerMensajeStringPaqueteMensajeEnumString() {
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constante, tipo, lenguaje));
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constanteFunciona, tipo, lenguaje));
		assertEquals("No se encontro el mensaje seleccionado: ", NHSPDDMensajes.obtenerMensaje(constante, tipo, lenguaje));
	}

	@Test
	public void testObtenerMensajeStringNullString() {
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constante, null, lenguaje));
		assertNotNull(NHSPDDMensajes.obtenerMensaje(constanteFunciona, null, lenguaje));
		assertEquals("No se encontro el mensaje seleccionado: ", NHSPDDMensajes.obtenerMensaje(constante, null, lenguaje));
	}

}
