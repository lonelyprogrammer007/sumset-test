package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEstructuraMetaAdministracionRegistrar.class,
		EstructuraMetaAdministracionRegistrar.class })
public class EstructuraMetaAdministracionRegistrarTest {

	@Autowired
	IEstructuraMetaAdministracionRegistrar registrar;

	@MockBean
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	@MockBean
	EstructuraMetaMapper estructuraMetaMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testCrearEstructuraMeta() throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO idLsUnidadMedida = new ArgumentoListaSimpleDTO();
		ArgumentoListaSimpleDTO idLsVerbo = new ArgumentoListaSimpleDTO();
		EstructuraMetaDTO estructura = new EstructuraMetaDTO();
		EstructuraMetaDTO auxiliar = new EstructuraMetaDTO();
		EstructuraMetaDTO res;
		estructura.setIdLsUnidadMedida(1L);
		estructura.setIdLsVerbo(1L);
		estructura.setIdEstructuraMetas(1L);
		auxiliar.setIdEstructuraMetas(1L);

		// if (idLsUnidadMedida == null || idLsUnidadMedida.getIdArgumento() == null) {
		idLsVerbo.setIdArgumento(1L);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(estructura.getIdLsUnidadMedida())).thenReturn(null)
				.thenReturn(idLsVerbo);
		res = registrar.crearEstructuraMeta(estructura);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

		// if (idLsVerbo == null || idLsVerbo.getIdArgumento() == null) {

		idLsUnidadMedida.setIdArgumento(1L);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(estructura.getIdLsUnidadMedida()))
				.thenReturn(idLsUnidadMedida).thenReturn(null);
		res = registrar.crearEstructuraMeta(estructura);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

		// if(respuesta.getIdEstructuraMetas()!=null) {

		auxiliar.setIdEstructuraMetas(null);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(estructura.getIdLsUnidadMedida()))
				.thenReturn(idLsUnidadMedida);
		when(sessionFacadeAFS.guardarEstructuraMeta(estructura)).thenReturn(auxiliar);

		res = registrar.crearEstructuraMeta(estructura);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

		// else
		auxiliar.setIdEstructuraMetas(4L);

		res = registrar.crearEstructuraMeta(estructura);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
