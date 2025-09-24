package co.gov.sdp.spdd.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

/**
 * Clase que permite realizar peticiones post,get,put y delete al servicio web
 * de NHSPDD
 *
 * @author Bryan Munoz
 */
@Component
public class MetodosRest<T> {

	private HttpStatus status;
	@Value("${com.direccion.url.seguridad}")
	private String server;
	private RestTemplate rest;
	private HttpHeaders headers;

	/**
	 * Metodo constructor de la clase
	 */
	public MetodosRest() {

		rest = new RestTemplate();
		headers = new HttpHeaders();
		headers.add(NHSPDDConstantes.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
		headers.add(NHSPDDConstantes.HEADER_ACCEPT, "*/*");

	}

	/**
	 * Metodo que permite realizar una peticion post a un servicio web
	 *
	 * @param dto El dto que se utilizara para realizar la peticion post
	 * @param c   la clase del dto para identificarlo
	 * @param uri la url a la que enviaremos el dto
	 * @return la respuesta de la api
	 */
	public T post(String dto, ParameterizedTypeReference<T> c, String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<>(dto, headers);
		ResponseEntity<T> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, c);
		setStatus(responseEntity.getStatusCode());
		return (T) responseEntity.getBody();
	}

	/**
	 * Metodo que permite realizar una peticion get a un servicio web
	 *
	 * @param uri url a la que se realizara la peticion
	 * @param c   clase para saber lo que devolvera la peticion
	 * @return una lista, objeto o consulta solicitada
	 */

	public T get(String uri, ParameterizedTypeReference<T> c) {
		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
		ResponseEntity<T> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, c);
		this.setStatus(responseEntity.getStatusCode());
		return (T) responseEntity.getBody();
	}

	/**
	 * Metodo que permite realizar una peticion put a un servicio web
	 *
	 * @param dto objeto que se enviara por la peticion put
	 * @param c   clase del objeto que se esta enviando
	 * @param uri url a la que se desea relizar la peticion
	 */
	public void put(T dto, Class<T> c, String uri) {
		HttpEntity<T> requestEntity = new HttpEntity<>(dto, headers);
		ResponseEntity<T> responseEntity = rest.exchange(server + uri, HttpMethod.PUT, requestEntity, c);
		setStatus(responseEntity.getStatusCode());
		this.setStatus(responseEntity.getStatusCode());

	}

	/**
	 * Metodo que permite realizar una peticion post a un servicio web
	 *
	 * @param dto El dto que se utilizara para realizar la peticion post
	 * @param c   la clase del dto para identificarlo
	 * @param uri la url a la que enviaremos el dto
	 * @return la respuesta de la api
	 */
	public T postRespuestaApi(String datos, ParameterizedTypeReference<T> bean, String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<>(datos, headers);
		ResponseEntity<T> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, bean);
		this.setStatus(responseEntity.getStatusCode());
		return (T) responseEntity.getBody();
	}

	
	public void agregarToken(String token) {
		headers.clear();
		headers.add(NHSPDDConstantes.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
		headers.add(NHSPDDConstantes.HEADER_ACCEPT, "*/*");
		headers.add("Authorization", NHSPDDConstantes.TOKEN_PREFIX+token);
	}
	
	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * Metodo que permite realizar una peticion delete a un servicio web
	 *
	 * @param uri url a la que se le realizar la peticion
	 */
//	public void delete(String uri) {
//		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
//		// ResponseEntity<String> responseEntity = rest.exchange(server + uri,
//		// HttpMethod.DELETE, requestEntity, null);
//		// this.setStatus(responseEntity.getStatusCode());
//	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the headers
	 */
	public HttpHeaders getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

}
