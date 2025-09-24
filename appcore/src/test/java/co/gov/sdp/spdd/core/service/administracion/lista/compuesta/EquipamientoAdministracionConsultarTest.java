package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IEquipamentoRepo;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
import co.gov.sdp.spdd.data.model.afs.Equipamento;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEquipamientoAdministracionConsultar.class,
		EquipamientoAdministracionConsultar.class })
public class EquipamientoAdministracionConsultarTest {

	@Autowired
	IEquipamientoAdministracionConsultar consultar;

	@MockBean
	IEquipamientoServiceRepo equipamientoServiceRepo;

	@MockBean
	IEquipamentoRepo equipamentoRepo;

	@MockBean
	EquipamientoMapper equipamientoMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testObtenerEquipamientoTodos() throws SpddExceptions {

		List<Equipamento> lista = new ArrayList<>();
		when(equipamientoServiceRepo.obtenerTodos()).thenReturn(lista);
		when(equipamientoMapper.equipamientoEntitiesToDTO(lista)).thenReturn(new ArrayList<EquipamientoDTO>());
		GenericoDTO res = consultar.obtenerEquipamientoTodos();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);

	}

	@Test
	public void testObtenerPaginado() throws SpddExceptions, JsonProcessingException {

		Long id = 1L;
		EquipamientoDTO peticion = new EquipamientoDTO();
		peticion.setIdEquipamento(id);
		when(sessionFacadeAFS.consultarEquipamientoPorFiltro(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
