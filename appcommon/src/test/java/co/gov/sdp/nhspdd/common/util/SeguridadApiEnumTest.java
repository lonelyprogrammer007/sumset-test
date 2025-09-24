package co.gov.sdp.nhspdd.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SeguridadApiEnumTest {

	@Test
	public void testEnum() {
		assertEquals(SeguridadApiEnum.CODIGO_OK.getCodigoRespuesta(), 0);
		assertEquals(SeguridadApiEnum.CODIGO_USUARIO_CONTRASENIA_EN_BLANCO.getCodigoRespuesta(), -1);
		assertEquals(SeguridadApiEnum.CODIGO_USUARIO_CONTRASENIA_INVALIDAS.getCodigoRespuesta(), -2);
		assertEquals(SeguridadApiEnum.CODIGO_TIPO_USUARIO_INVALIDO.getCodigoRespuesta(), -3);
		assertEquals(SeguridadApiEnum.CODIGO_USUARIO_ERROR_NO_EXISTE.getCodigoRespuesta(), -4);
		assertEquals(SeguridadApiEnum.CODIGO_LDAP_ERROR.getCodigoRespuesta(), -5);
		assertEquals(SeguridadApiEnum.CODIGO_ERROR_SERVICIO_NO_SE_ENCUENTRA.getCodigoRespuesta(), -6);
		assertEquals(SeguridadApiEnum.CODIGO_TOKEN_INVALIDO.getCodigoRespuesta(), -7);
		assertEquals(SeguridadApiEnum.CODIGO_USUARIO_USUARIO_INHABILITADO.getCodigoRespuesta(), -8);
		assertEquals(SeguridadApiEnum.CODIGO_USUARIO_CAMBIAR_CONTRASENIA.getCodigoRespuesta(), -9);
		assertEquals(SeguridadApiEnum.CODIGO_ROL_NO_EXISTE.getCodigoRespuesta(), -10);
		assertEquals(SeguridadApiEnum.CODIGO_USUARIO_NO_TIENE_PERMISO_APLICACION.getCodigoRespuesta(), -11);
		assertEquals(SeguridadApiEnum.CODIGO_ERROR.getCodigoRespuesta(), -99);
		assertEquals(SeguridadApiEnum.CODIGO_ADVERTENCIA.getCodigoRespuesta(), 2);
	}

}
