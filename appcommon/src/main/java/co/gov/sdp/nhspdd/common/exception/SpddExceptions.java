/**
 * 
 */
package co.gov.sdp.nhspdd.common.exception;

/**
 * @author Sumset
 *
 */
public class SpddExceptions extends Exception {

	public static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public SpddExceptions() {
		super();
	}
	
	/**
	 * 
	 * @param mensaje
	 */
	public SpddExceptions(String mensaje) {
		super(mensaje);
	}
	
	/**
	 * 
	 * @param causa
	 */
	public SpddExceptions(Throwable causa) {
		super(causa);
	}
	
	/**
	 * 
	 * @param mensaje
	 * @param causa
	 */
	public SpddExceptions(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
