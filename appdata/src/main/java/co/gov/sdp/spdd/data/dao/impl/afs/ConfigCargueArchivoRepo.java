package co.gov.sdp.spdd.data.dao.impl.afs;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfigCargueArchivoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IConfigCargueArchivoRepo;
import co.gov.sdp.spdd.data.model.afs.ConfigCargueArchivo;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class ConfigCargueArchivoRepo extends OperacionesBasicasFacade<ConfigCargueArchivo, Long>
		implements IConfigCargueArchivoServiceRepo {

	/**
	 * 
	 */
	@Autowired
	IConfigCargueArchivoRepo configuracionCargueArchivoRepo;

	/**
	 * 
	 */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ConfigCargueArchivo, Long> getRepo() {
		return configuracionCargueArchivoRepo;
	}

	/**
	 * 
	 */
	@Override
	public ConfigCargueArchivo obtenerConfigCarguePorArchivoProcesado(Long id) {
		return configuracionCargueArchivoRepo.obtenerConfigCargueArchivo(id);
	}

	/**
	 * 
	 * @param idLsModulo
	 * @param idLsTema
	 * @return
	 */
	@Override
	public ConfigCargueArchivo buscarPorIdLsModuloYIdLsTema(Long idLsModulo, Long idLsTema) {
		return configuracionCargueArchivoRepo.buscarPorIdLsModuloAndIdLsTema(idLsModulo, idLsTema);
	}

	@Override
	public int guardarSql(String strQuery) throws SpddExceptions {
		return jdbcTemplate.update(strQuery);
	}

	@Override
	public int buscarSql(String strQuery) throws SpddExceptions {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(strQuery);
		return result != null ? result.size() : 0;
	}
}
