package co.gov.sdp.nhspdd.common.util;

/**
 * Enumeracion para los distintos tipos de archivos de mensajes existentes
 *
 * @author Raul Londono Murillo
 *
 */
public enum PaqueteMensajeEnum {

    // Archivo de mensajes normales
    MENSAJES("mensajes.mensajes"),
    // Archivo de mensajes de error
    ERRORES("mensajes.errores");

    // Nombre del paquete de mensajes
    private String nombrePaquete;

    PaqueteMensajeEnum(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    @Override
    public String toString() {
        return nombrePaquete;
    }
}
