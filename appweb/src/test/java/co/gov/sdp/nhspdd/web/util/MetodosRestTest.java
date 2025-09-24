package co.gov.sdp.nhspdd.web.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import co.gov.sdp.spdd.web.util.MetodosRest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetodosRestTest<T> {

	@Autowired
	private MetodosRest<T> rest;
	@MockBean
	private RestTemplate template;
	@MockBean
	private HttpHeaders headers;

	private Class<T> c;

	@Test
	public void testPost() {
//	  template= new RestTemplate();
//		HttpEntity<T> requestEntity = new HttpEntity<>(null, headers);
//		when(template.exchange("http://localhost:8080/appcore"+"/prueba", HttpMethod.POST,requestEntity, c )).thenReturn(null);
//	rest.post(null,c,"/prueba");
	}


	@Test
	public void testGet() {

	}

	@Test
	public void testPut() {

	}

	@Test
	public void testGetStatus() {

	}

	@Test
	public void testSetStatus() {

	}

}
