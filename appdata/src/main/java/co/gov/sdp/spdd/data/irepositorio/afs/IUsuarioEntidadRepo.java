package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;

/**
 * Interfaz repositorio para manejar la conexion a la base de datos de la
 * entidad UsuarioEntidad
 *
 * @author Raul Londono Murillo
 *
 */
@Transactional
@Repository
public interface IUsuarioEntidadRepo extends CrudRepository<UsuarioEntidad, Long>, Serializable  {

    /**
     * Metodo de consulta a la base de datos que obtiene todos los
     * UsuarioEntidad asociados a un usuario especifico
     *
     * @param usuario Usuario para buscar en la base de datos
     * @return Lista de entidades resultado de la consulta
     */
    @Query("SELECT ue FROM UsuarioEntidad ue WHERE ue.codigoUsuario = :usuario")
    public List<UsuarioEntidad> obtenerPorUsuario(@Param("usuario") String usuario);
}
