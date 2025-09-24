package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.FuncionarioClaveEntidad;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IFuncionarioClaveEntidadRepo extends CrudRepository<FuncionarioClaveEntidad, Long>, Serializable {

	/**
	 * Metodo de consulta a la base de datos que obtiene todos los
	 * FuncionarioClaveEntidad asociados a una entidad
	 *
	 * @param codigoEntidad codigoEntidad para buscar en la base de datos
	 * @return Lista de entidades resultado de la consulta
	 */
	@Query("SELECT fce FROM FuncionarioClaveEntidad fce WHERE fce.codigoEntidad.codigoEntidad = :codigoEntidad")
	public List<FuncionarioClaveEntidad> obtenerFuncionarioPorEntidad(@Param("codigoEntidad") String codigoEntidad);

	@Query("SELECT fce FROM FuncionarioClaveEntidad fce WHERE fce.codigoEntidad.codigoEntidad= :codigoEntidad AND fce.idLsFuncion.idArgumento= :idFuncion")
	public FuncionarioClaveEntidad validarFuncionarioPorIdLsFuncionYEntidad(
			@Param("codigoEntidad") String codigoEntidad, @Param("idFuncion") Long idLsFuncion);
}
