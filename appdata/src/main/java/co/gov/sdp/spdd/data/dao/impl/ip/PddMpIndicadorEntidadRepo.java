package co.gov.sdp.spdd.data.dao.impl.ip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpIndicadorEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMpIndicadorEntidadRepo;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicadorEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddMpIndicadorEntidadRepo extends OperacionesBasicasFacade<PddMpIndicadorEntidad, Long>
		implements IPddMpIndicadorEntidadServiceRepo {

	@Autowired
	IPddMpIndicadorEntidadRepo indicadorEntidadRepo;

	@Override
	public CrudRepository<PddMpIndicadorEntidad, Long> getRepo() {
		return indicadorEntidadRepo;
	}

	@Override
	public Page<PddMpIndicadorEntidad> obtenerTodosIndicadoresMetaEntidad(Long idIndProd, Pageable pageable)
			throws SpddExceptions {
		return indicadorEntidadRepo.obtenerEntidadesPorIndicadorProducto(idIndProd, pageable);
	}

	@Override
	public PddMpIndicadorEntidad validarIndicadorEntidad(String codigoEntidad, Long idIndProd) throws SpddExceptions {
		return indicadorEntidadRepo.validarIndicadorEntidadPorEntidadEIndicadorMetaProducto(codigoEntidad, idIndProd);
	}

	@Override
	public Long sumarPonderacionesEntidadesMetaProducto(Long idMetaProdInd) throws SpddExceptions {
		return indicadorEntidadRepo.sumarPonderacionesEntidadesIndicadorMp(idMetaProdInd);
	}

}
