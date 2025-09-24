package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;

/**
 * Interfaz repositorio para manejar la conexion a la base de datos de la
 * entidad ProyectoInversion
 *
 * @author Raul Londoño Murillo
 *
 */
@Transactional
@Repository
public interface IProyectoInversionRepo extends CrudRepository<ProyectoInversion, Long>, Serializable  {

    /**
     * Metodo de consulta a la base de datos que obtiene todos los
     * ProyectosInversion sin asociar
     *
     * @return
     */
    @Query("SELECT pi FROM ProyectoInversion pi WHERE pi.idProyectoInversion not in (select pu.idProyectoInversion from ProyectosInversionUsuario pu)")
    public List<ProyectoInversion> obtenerDisponibles();
}
