package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotObraEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotObraEntidadRepo;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository de la entidad PotObraEntidad
 * @author DANIEL
 * @version 1.0 30/04/2020
 */
@Transactional
@Service
public class PotObraEntidadRepo extends OperacionesBasicasFacade<PotObraEntidad, Long> implements IPotObraEntidadServiceRepo, Serializable{

	@Autowired
	IPotObraEntidadRepo potObraEntidadRepository;
	
	@Override
	public CrudRepository<PotObraEntidad, Long> getRepo() {
		return potObraEntidadRepository;
	}
	
	@Override
	public List<PotObraEntidad> obtenerTodosPorIdPotObra(Long idPotObra) {
		return potObraEntidadRepository.obtenerTodosPorIdPotObra(idPotObra);
	}

	@Override
	public void eliminarTodos(List<PotObraEntidad> listaPotObraEntidad) {
		potObraEntidadRepository.deleteAll(listaPotObraEntidad);
	}

	@Override
	public PotObraEntidad obtenerPotCodigoEntidadYIdPotObra(String codigoEntidad, Long idPotObra) {
		return potObraEntidadRepository.obtenerPotCodigoEntidadYIdPotObra(codigoEntidad, idPotObra);
	}
}
