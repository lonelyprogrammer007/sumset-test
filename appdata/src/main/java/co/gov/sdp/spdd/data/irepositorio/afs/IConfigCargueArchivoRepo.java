package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ConfigCargueArchivo;

@Transactional
@Repository
public interface IConfigCargueArchivoRepo extends CrudRepository<ConfigCargueArchivo, Long>, Serializable{

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Query("SELECT cca FROM ConfigCargueArchivo cca JOIN ArchivoProcesado ap ON cca.idConfigCargue = ap.idConfigCargue WHERE ap.idArchivo = :id")
	public ConfigCargueArchivo obtenerConfigCargueArchivo(@Param("id") Long id);
	
	/**
	 * 
	 * @param idLsModulo
	 * @param idLsTema
	 * @return
	 */

	@Query("SELECT cca FROM ConfigCargueArchivo cca WHERE cca.idLsModulo.idArgumento = :idLsModulo AND cca.idLsTema.idArgumento = :idLsTema")
	 public ConfigCargueArchivo buscarPorIdLsModuloAndIdLsTema(@Param("idLsModulo") Long idLsModulo, @Param("idLsTema") Long idLsTema);

}
