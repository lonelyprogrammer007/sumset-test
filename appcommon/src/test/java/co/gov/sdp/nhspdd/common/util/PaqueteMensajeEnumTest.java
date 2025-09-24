package co.gov.sdp.nhspdd.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PaqueteMensajeEnumTest {

    @Test
    public void testPaqueteMensajeEnum() {
        PaqueteMensajeEnum mensaje = PaqueteMensajeEnum.MENSAJES;
        assertEquals(mensaje instanceof PaqueteMensajeEnum, true);
    }

}
