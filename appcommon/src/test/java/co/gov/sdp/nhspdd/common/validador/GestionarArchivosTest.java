package co.gov.sdp.nhspdd.common.validador;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import co.gov.sdp.nhspdd.common.util.GestionarArchivos;

public class GestionarArchivosTest {

	@Test
	public void testLeerArchivo() {
		try {
			InputStream archivo = IOUtils.toInputStream("Prueba;Prueba 1", "UTF-8");
			GestionarArchivos gestionarArchivos = new GestionarArchivos();
			assertNotNull(gestionarArchivos.leerArchivo(archivo, ";"));
		} catch (IOException e) {
		}
	}

	@Test
	public void testXmlStringToDTO() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
"<tablasDTO>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>true</esValidacion>\r\n" + 
"        <longitud>4</longitud>\r\n" + 
"        <nombreCampo>YEAR</nombreCampo>\r\n" + 
"        <posicion>1</posicion>\r\n" + 
"        <tipoDato>INTEGER</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>true</esValidacion>\r\n" + 
"        <longitud>2</longitud>\r\n" + 
"        <nombreCampo>MES</nombreCampo>\r\n" + 
"        <posicion>2</posicion>\r\n" + 
"        <tipoDato>INTEGER</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>true</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>5</longitud>\r\n" + 
"        <nombreCampo>CODIGO_DISTRITAL</nombreCampo>\r\n" + 
"        <posicion>3</posicion>\r\n" + 
"        <tipoDato>CHAR</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>true</esValidacion>\r\n" + 
"        <longitud>6</longitud>\r\n" + 
"        <nombreCampo>CODIGO_PROYECTO</nombreCampo>\r\n" + 
"        <posicion>4</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>300</longitud>\r\n" + 
"        <nombreCampo>NOMBRE_PROYECTO</nombreCampo>\r\n" + 
"        <posicion>5</posicion>\r\n" + 
"        <tipoDato>CHAR</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>EJECUCION_VIGENCIA</nombreCampo>\r\n" + 
"        <posicion>6</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>GIROS_VIGENCIA</nombreCampo>\r\n" + 
"        <posicion>7</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>RECURSOS_SUSPENDIDOS</nombreCampo>\r\n" + 
"        <posicion>8</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>CONSTITUCION_RESERVA</nombreCampo>\r\n" + 
"        <posicion>9</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>APROPIACION_RESERVA</nombreCampo>\r\n" + 
"        <posicion>10</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>APROPIACION_DEFINITIVA</nombreCampo>\r\n" + 
"        <posicion>11</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>15</longitud>\r\n" + 
"        <nombreCampo>EJECUCION_GIRO_RESERVAS</nombreCampo>\r\n" + 
"        <posicion>12</posicion>\r\n" + 
"        <tipoDato>DECIMAL</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>30</longitud>\r\n" + 
"        <nombreCampo>CODIGO_CLASIF_PRESUPUESTAL</nombreCampo>\r\n" + 
"        <posicion>13</posicion>\r\n" + 
"        <tipoDato>CHAR</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>6</longitud>\r\n" + 
"        <nombreCampo>CODIGO_INTERNO</nombreCampo>\r\n" + 
"        <posicion>14</posicion>\r\n" + 
"        <tipoDato>INTEGER</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>false</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>10</longitud>\r\n" + 
"        <nombreCampo>ORIGEN</nombreCampo>\r\n" + 
"        <posicion>15</posicion>\r\n" + 
"        <tipoDato>CHAR</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>true</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>10</longitud>\r\n" + 
"        <nombreCampo>ID_ARCHIVO</nombreCampo>\r\n" + 
"        <posicion>16</posicion>\r\n" + 
"        <tipoDato>INTEGER</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>true</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>5</longitud>\r\n" + 
"        <nombreCampo>ID_PLAN_DESARROLLO</nombreCampo>\r\n" + 
"        <posicion>17</posicion>\r\n" + 
"        <tipoDato>INTEGER</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <columnas>\r\n" + 
"        <esLlaveForanea>true</esLlaveForanea>\r\n" + 
"        <esValidacion>false</esValidacion>\r\n" + 
"        <longitud>5</longitud>\r\n" + 
"        <nombreCampo>CODIGO_N3</nombreCampo>\r\n" + 
"        <posicion>18</posicion>\r\n" + 
"        <tipoDato>INTEGER</tipoDato>\r\n" + 
"    </columnas>\r\n" + 
"    <nombreTabla>SPDD_INFORMACION_PRESUPUESTAL</nombreTabla>\r\n" + 
"</tablasDTO>\r\n";
	}

	@Test
	public void testComparaArchivosXML() {
		assertNotNull(NHSPDDCamposValidar.removerComponenteUsuario());
	}

	@Test
	public void testValidarLineaXML() {
		assertNotNull(NHSPDDCamposValidar.iniciarSesionObtenerCamposValidacion());
	}

	@Test
	public void testArmarErrorFila() {
		assertNotNull(NHSPDDCamposValidar.restablcerCorreoCamposValidacion());
	}

}
