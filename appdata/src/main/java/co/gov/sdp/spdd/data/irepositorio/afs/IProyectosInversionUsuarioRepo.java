package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;

/**
 * Interfaz repositorio para manejar la conexion a la base de datos de la
 * entidad ProyectosInversionUsuario
 *
 * @author Raul Londono Murillo
 *
 */
@Transactional
@Repository
public interface IProyectosInversionUsuarioRepo extends CrudRepository<ProyectosInversionUsuario, Long>, Serializable  {

    /**
     * Metodo de consulta a la base de datos que obtiene todos los
     * ProyectosInversionUsuario asociados a un usuario especifico
     *
     * @param usuario Usuario para buscar en la base de datos
     * @return Lista de entidades resultado de la consulta
     */
    @Query("SELECT piu FROM ProyectosInversionUsuario piu WHERE piu.codigoUsuario = :usuario")
    public List<ProyectosInversionUsuario> obtenerPorUsuario(@Param("usuario") String usuario);
}
