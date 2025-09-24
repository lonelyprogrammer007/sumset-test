package co.gov.sdp.spdd.web.afs.icontroller.administracion;

/**
 * Interfaz de lista compuesta que implementa los metodos del controlador
 *
 * @author Raul Londono Murillo
 *
 */
public interface IListaCompuestaController {

	/**
	 * Metodo que trae la pantalla donde se muestran todas las listas compuestas
	 *
	 * @return String con la ubicacion de la pagina
	 */

	public String consultarTablaListaCompuesta(Long id);
}
