package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEquipamientoAdministracionModificar.class, EquipamientoAdministracionModificar.class})
class EquipamientoAdministracionModificarTest {

	@Autowired
	IEquipamientoAdministracionModificar modificar;
	
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

	@Test
	void testModificarEquipamiento() throws SpddExceptions {
		
		EquipamientoDTO entidad = new EquipamientoDTO(), auxiliar = new EquipamientoDTO(), equipamientoDto = new EquipamientoDTO(), respuesta = new EquipamientoDTO();
	
		entidad.setIdEquipamento(1L);
		auxiliar.setIdEquipamento(1L);
		equipamientoDto.setIdEquipamento(1L);
		equipamientoDto.setIdLsCategoria(2L);
		equipamientoDto.setIdLsSector(3L);
		respuesta.setIdEquipamento(1L);
		
		EquipamientoDTO res;
		
		//if (entidad.getIdEquipamento() == auxiliar.getIdEquipamento()) {
		
		when(sessionFacadeAFS.consultarEquipamiento(equipamientoDto.getIdEquipamento())).thenReturn(entidad);
		when(sessionFacadeAFS.buscarSectorYCategoria(equipamientoDto.getIdLsSector(), equipamientoDto.getIdLsCategoria())).thenReturn(auxiliar);
		when(sessionFacadeAFS.modificarEquipamiento(equipamientoDto)).thenReturn(auxiliar);
		
        res = modificar.modificarEquipamiento(equipamientoDto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		//if (respuesta.getIdEquipamento() != null) {
		auxiliar.setIdEquipamento(2L);
		when(sessionFacadeAFS.guardarEquipamiento(equipamientoDto)).thenReturn(respuesta);
		
		res = modificar.modificarEquipamiento(equipamientoDto);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		// else
		
		 respuesta.setIdEquipamento(null);
	        res = modificar.modificarEquipamiento(equipamientoDto);
			assertNotNull(res);
	        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	        assertEquals("La asociación creada ya se encuentra registrada", res.getMsgRespuesta());
	        
	        // else
	        when(sessionFacadeAFS.consultarEquipamiento(equipamientoDto.getIdEquipamento())).thenReturn(null);
	        res = modificar.modificarEquipamiento(equipamientoDto);
			assertNotNull(res);
	        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	        assertEquals("No se encontro la entidad seleccionada", res.getMsgRespuesta());    
	}

}
