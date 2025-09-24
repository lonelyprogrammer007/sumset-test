package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMeta;

public interface IPddMetaRepo extends CrudRepository<PddMeta, Long>, Serializable {
	
	/**
	 * 
	 * @param idEspecifico
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT pm FROM PddMeta pm WHERE pm.idEspecifico.idEspecifico = :idEspecifico")
	public List<PddMeta> consultarMetasCompromistoEstrategico(@Param("idEspecifico") Long idEspecifico) throws SpddExceptions;
	
	/**
	 * 
	 * @param meta
	 * @param especifico
	 * @param idTipoMeta
	 * @return
	 */
	@Query("SELECT pm FROM PddMeta pm WHERE pm.meta= :meta AND pm.idEspecifico.idEspecifico= :especifico AND pm.idTipoMetaLs.idArgumento= :idTipoMeta")
	public PddMeta consultarPddMetaPorMetaYIdTipoMetaYIdEspecifico(@Param("meta") String meta, @Param("especifico") Long especifico,@Param("idTipoMeta") Long idTipoMeta) throws SpddExceptions;
}
