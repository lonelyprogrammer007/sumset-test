package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ComponenteGasto;

@Transactional
@Repository
public interface IComponenteGastoRepo extends CrudRepository<ComponenteGasto, Long>, Serializable  {

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @return
	 */
	@Query("SELECT cg FROM ComponenteGasto cg WHERE lower(cg.codigoComponente)= lower(:codigo) AND lower(cg.nombreComponente)= lower(:nombre)")
	public ComponenteGasto obtenerCodigoYNombre(@Param("codigo")Long codigo,@Param("nombre") String nombre);
}
