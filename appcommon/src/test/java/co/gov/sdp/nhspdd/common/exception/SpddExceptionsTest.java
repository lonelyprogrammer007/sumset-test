/**
 * 
 */
package co.gov.sdp.nhspdd.common.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


/**
 * @author Sumset
 *
 */
public class SpddExceptionsTest {

	@Test
	public void testConstructor() {
		assertNotNull(new SpddExceptions());
		assertNotNull(new SpddExceptions("Prueba excepcion"));
		assertNotNull(new SpddExceptions(new Exception("Prueba excepcion")));
		assertNotNull(new SpddExceptions("Prueba excepcion", new Exception("Prueba excepcion")));
	}

}
