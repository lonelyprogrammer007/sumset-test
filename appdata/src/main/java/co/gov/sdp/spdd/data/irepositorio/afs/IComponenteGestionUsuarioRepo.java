package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;

/**
 * Repositorio de la clase ComponenteGestionUsuario
 *
 * @author Bryan Munoz
 *
 */
@Transactional
@Repository
public interface IComponenteGestionUsuarioRepo extends CrudRepository<ComponenteGestionUsuario, Long>, Serializable  {

    /**
     * Consulta que me trae los componentes por usuario
     *
     * @param usuario un usuario tipo string
     * @return una lista de componentes que tenga el usuario
     */
    @Query("SELECT c FROM ComponenteGestionUsuario c WHERE c.codigoUsuario = :usuario")
    public List<ComponenteGestionUsuario> findByUsuario(@Param("usuario") String usuario);

}
