package co.gov.sdp.spdd.core.service.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IAdministracionListaSimpleModificar.class, AdministracionListaSimpleModificar.class})
public class AdministracionListaSimpleModificarTest {

	@Autowired
	IAdministracionListaSimpleModificar modificar;
	
	@MockBean
	ListaSimpleMapper listaSimpleMapper;

	@MockBean
	IListaSimpleServiceRepo listaSimpleServiceRepo;

	@MockBean
	SPDDLogger spddLogger;
	
	
	@Test
	public void testModificarListaSimple() throws SpddExceptions {
		Long id=1L;	
		ListaSimple lista=new ListaSimple();
        lista.setIdListaSimple(1L);
        ListaSimpleDTO peticion = new ListaSimpleDTO();
        peticion.setIdListaSimple(id);
        ListaSimpleDTO res;
        
        // Prueba del if
		when(listaSimpleServiceRepo.obtener(id)).thenReturn(lista);
		when(listaSimpleMapper.listaSimpleDTOToEntity(peticion)).thenReturn(lista);
		when(listaSimpleServiceRepo.guardar(lista)).thenReturn(lista);
		when(listaSimpleMapper.listaSimpleEntityToDTO(lista)).thenReturn(new ListaSimpleDTO());
		res = modificar.modificarListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		//prueba del else
		when(listaSimpleServiceRepo.obtener(id)).thenReturn(null);
		res = modificar.modificarListaSimple(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
      
		
	}

}
