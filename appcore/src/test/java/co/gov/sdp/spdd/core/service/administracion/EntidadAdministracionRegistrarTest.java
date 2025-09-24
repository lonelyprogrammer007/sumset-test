package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBancoDeProyectoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IUsuarioEntidadRepo;
import co.gov.sdp.spdd.data.mapper.EntidadMapper;
import co.gov.sdp.spdd.data.mapper.UsuarioEntidadMapper;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.afs.BancoDeProyectos;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, EntidadAdministracionRegistrar.class,
		IEntidadAdministracionRegistrar.class })
public class EntidadAdministracionRegistrarTest {

	
	@Autowired
	IEntidadAdministracionRegistrar registrar;

	@MockBean
	IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;

	@MockBean
	IUsuarioEntidadRepo repo;

	// Repositorio de entidad
	@MockBean
	IEntidadServiceRepo entidadServiceRepo;

	// Repositorio de banco
	@MockBean
	IBancoDeProyectoServiceRepo bancoDeProyectoServiceRepo;

	// Repositorio de banco
	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de entidad
	@MockBean
	EntidadMapper entidadMapper;

	// Mapper de usuario entidad
	@MockBean
	UsuarioEntidadMapper usuarioEntidadMapper;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	ISessionFacadeAFS sessionFacadeAfs;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testCrearEntidad() throws SpddExceptions {
		ArgumentoListaSimple idLsAsociacion= new ArgumentoListaSimple();
		ArgumentoListaSimple idLsClasificacion = new ArgumentoListaSimple();
		BancoDeProyectos banco = new BancoDeProyectos();
		
		EntidadDTO entidad = new EntidadDTO();
		EntidadDTO respuesta = new EntidadDTO();
		entidad.setIdBancoProyecto(1L);
		entidad.setIdLsAsociacion(1L);
		entidad.setIdLsClasificacion(1L);
		Entidad auxiliar = new Entidad();
		
		EntidadDTO res;
		
		entidad.setIdBancoProyecto(1L);

		
		//if (bancoDeProyectos == null || bancoDeProyectos.getIdBancoProyecto() == null) {
		banco.setIdBancoProyecto(1L);
		when(bancoDeProyectoServiceRepo.obtener(entidad.getIdBancoProyecto())).thenReturn(null);
		res = registrar.crearEntidad(entidad);
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	    
	    // if (idLsAsociacion == null || idLsAsociacion.getIdArgumento() == null) {
	    
	    when(bancoDeProyectoServiceRepo.obtener(entidad.getIdBancoProyecto())).thenReturn(banco);
		when(argumentoListaSimpleServiceRepo.obtener(entidad.getIdLsAsociacion())).thenReturn(null);
		res = registrar.crearEntidad(entidad);
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	
		
	    // if (idLsClasificacion == null || idLsClasificacion.getIdArgumento() == null) {
	    
	    idLsAsociacion.setIdArgumento(1L);
	    when(bancoDeProyectoServiceRepo.obtener(entidad.getIdBancoProyecto())).thenReturn(banco);
	    when(argumentoListaSimpleServiceRepo.obtener(entidad.getIdLsClasificacion())).thenReturn(idLsAsociacion).thenReturn(null);
	    res = registrar.crearEntidad(entidad);
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	    
	    
	    
	    when(entidadMapper.entidadDTOToEntity(entidad)).thenReturn(auxiliar);
	    when(entidadServiceRepo.guardar(auxiliar)).thenReturn(null);
	    when(entidadMapper.entidadEntityToDTO(auxiliar)).thenReturn(respuesta);
	    when(bancoDeProyectoServiceRepo.obtener(entidad.getIdBancoProyecto())).thenReturn(banco);
	    when(argumentoListaSimpleServiceRepo.obtener(entidad.getIdLsClasificacion())).thenReturn(idLsAsociacion).thenReturn(idLsClasificacion);
	    
	    res = registrar.crearEntidad(entidad);
	    assertNotNull(res);
	
	    

	}



}
