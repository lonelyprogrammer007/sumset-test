package co.gov.sdp.nhspdd.common.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Clase para gestionar los mensajes del archivo de configuracion
 *
 * @author Raul Londono Murillo
 *
 */
public class NHSPDDMensajes {

    private NHSPDDMensajes() {
        super();
    }

    /**
     * Metodo que obtiene un mensaje dada una constante de mensaje y un
     * lenguaje, usa el archivo de mensajes por defecto
     *
     * @param constante Constante para obtener mensaje
     * @param lenguaje Modificador de lenguaje, puede ser <code>Null</code> para
     * usar el lenguaje por efecto
     * @return El mensaje de la constante, o <code>Null</code> en caso de no
     * encontrarlo
     */
    public static String obtenerMensaje(String constante, String lenguaje) {
        String tipoMensaje = PaqueteMensajeEnum.MENSAJES.getNombrePaquete();
        Locale idioma = new Locale("");
        if (lenguaje != null) {
            idioma = new Locale(lenguaje);
        }
        try {
        	ResourceBundle resource =  ResourceBundle.getBundle(tipoMensaje, idioma);
            return resource.getString(constante);
        } catch (MissingResourceException exception) {
            return NHSPDDConstantes.MENSAJE_NO_ENCONTRADO + ": " + constante;
        }
    }

    /**
     * Metodo que obtiene un mensaje dada una constante de mensaje y un archivo
     * de mensajes, usa el lenguaje por defecto
     *
     * @param constante Constante para obtener mensaje
     * @param tipo Tipo de mensajes para elegir el archivo a utilizar, puede ser
     * <code>Null</code> para usar el lenguaje por efecto
     * @return El mensaje de la constante, o <code>Null</code> en caso de no
     * encontrarlo
     */
    public static String obtenerMensaje(String constante, PaqueteMensajeEnum tipo) {
        String tipoMensaje = PaqueteMensajeEnum.MENSAJES.getNombrePaquete();
        if (tipo != null) {
            tipoMensaje = tipo.getNombrePaquete();
        }
        try {
        	ResourceBundle resource =  ResourceBundle.getBundle(tipoMensaje);
            return resource.getString(constante);
        } catch (MissingResourceException exception) {
            return NHSPDDConstantes.MENSAJE_NO_ENCONTRADO + ": " + constante;
        }
    }

    /**
     * Metodo que obtiene un mensaje dada una constante de mensaje, un archivo
     * de mensajes y un lenguaje
     *
     * @param constante Constante para obtener mensaje
     * @param tipo Tipo de mensajes para elegir el archivo a utilizar, puede ser
     * <code>Null</code> para usar el lenguaje por efecto
     * @param lenguaje Modificador de lenguaje, puede ser <code>Null</code> para
     * usar el lenguaje por efecto
     * @return El mensaje de la constante, o <code>Null</code> en caso de no
     * encontrarlo
     */
    public static String obtenerMensaje(String constante, PaqueteMensajeEnum tipo, String lenguaje) {
        Locale idioma = new Locale("");
        if (lenguaje != null) {
            idioma = new Locale(lenguaje);
        }
        String tipoMensaje = PaqueteMensajeEnum.MENSAJES.getNombrePaquete();
        if (tipo != null) {
            tipoMensaje = tipo.getNombrePaquete();
        }
        try {
            ResourceBundle resource = ResourceBundle.getBundle(tipoMensaje, idioma);
            return resource.getString(constante);
        } catch (MissingResourceException exception) {
            return NHSPDDConstantes.MENSAJE_NO_ENCONTRADO + ": " + constante;
        }
    }

}
