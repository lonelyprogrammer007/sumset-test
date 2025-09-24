package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMpEntidadRepo;
import co.gov.sdp.spdd.data.model.ip.PddMpEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddMpEntidadRepo extends OperacionesBasicasFacade<PddMpEntidad, Long>
		implements IPddMpEntidadServiceRepo, Serializable {

	@Autowired
	IPddMpEntidadRepo pddMpEntidadRepositorio;

	@Override
	public CrudRepository<PddMpEntidad, Long> getRepo() {
		return pddMpEntidadRepositorio;
	}

	@Override
	public List<PddMpEntidad> obtenerTodasMpEntidadPorMetaProducto(Long idMetaProducto, Pageable pageable)
			throws SpddExceptions {
		return pddMpEntidadRepositorio.obtenerMpEntidadPorMetaProducto(idMetaProducto, pageable);
	}

	@Override
	public PddMpEntidad validarMpEntidadPorMetaProductoYEntidad(String codigoEntidad, Long idMetaProducto)
			throws SpddExceptions {
		return pddMpEntidadRepositorio.validarMpEntidadPorMetaProductoYEntidad(codigoEntidad, idMetaProducto);
	}

}
