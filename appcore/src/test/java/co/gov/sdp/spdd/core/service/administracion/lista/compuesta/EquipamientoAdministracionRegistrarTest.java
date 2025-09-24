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
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEquipamientoAdministracionRegistrar.class, EquipamientoAdministracionRegistrar.class})
public class EquipamientoAdministracionRegistrarTest {

	@Autowired
	IEquipamientoAdministracionRegistrar registrar;
	
	@MockBean
	IEquipamientoServiceRepo equipamientoServiceRepo;

	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	@MockBean
	EquipamientoMapper equipamientoMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	public void testCrearEquipamiento() throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO categoria = new ArgumentoListaSimpleDTO();
		ArgumentoListaSimpleDTO sector = new ArgumentoListaSimpleDTO();
		EquipamientoDTO equipamiento = new EquipamientoDTO();
		EquipamientoDTO auxiliar = new EquipamientoDTO();
		EquipamientoDTO res;
		equipamiento.setIdLsCategoria(1L);
		equipamiento.setIdLsSector(1L);
		equipamiento.setIdEquipamento(1L);
		auxiliar.setIdEquipamento(1L);

		
		//	if (idLsCategoria == null || idLsCategoria.getIdArgumento() == null) {
		
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(equipamiento.getIdLsCategoria())).thenReturn(null);
		//when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(equipamiento.getIdLsSector())).thenReturn(sector);
		
		res = registrar.crearEquipamiento(equipamiento);
	
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	    
	    
		//if (idLsSector == null || idLsSector.getIdArgumento() == null) {
	    categoria.setIdArgumento(2L);

		when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(equipamiento.getIdLsSector())).thenReturn(categoria).thenReturn(null);

		res = registrar.crearEquipamiento(equipamiento);
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	    
	    //if (respuesta.getIdEquipamento() == null) {
	    categoria.setIdArgumento(3L);
	    auxiliar.setIdEquipamento(null);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(equipamiento.getIdLsCategoria())).thenReturn(categoria);
	    when(sessionFacadeAFS.guardarEquipamiento(equipamiento)).thenReturn(auxiliar);

	    res = registrar.crearEquipamiento(equipamiento);
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	    
	    //else
	    auxiliar.setIdEquipamento(4L);
   
	    res = registrar.crearEquipamiento(equipamiento);
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
