package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.Pdl;

/**
 * 
 * @author sumset
 *
 */
@Repository
public interface IPddRepo extends CrudRepository<Pdd, Long>, Serializable {
	
    /**
     * Metodo de consulta a la base de datos que obtiene todos los
     * ProyectosInversion sin asociar
     *
     * @return
     */
    @Query("SELECT p FROM Pdd p")    
    public List<Pdd> obtenerTodosPdd();
    
    /**
     * Metodo que permite consultar los PDD de un estado en especifico
     * @param idEstado Long que representa el identificador del estado por el cual se quiere hacer la busqueda
     * @return una lista de entidades PDD correspondientes a la busqueda o null en caso contrario
     */
    @Query("SELECT p FROM Pdd p WHERE p.idLsEstadoPlan.idArgumento= :estado")
    public List<Pdd> obtenerPorEstado(@Param("estado") Long idEstado);
    
    /**
     * Metodo que permite consultar un PDD por el identifcador
     * @param idPlan Long que represneta el identificador del plan de desarrollo a buscar
     * @return un objeto de Pdd con la informacion correspondiente
     */
    @Query("SELECT p FROM Pdd p WHERE p.idPlanDesarrollo= :plan")
    public Pdd obtenerPorId(@Param("plan") Long idPlan);
    
    /**
     * Metodo que permite obtenre todos los PDD en orden ascendente
     * @return
     */
    @Query("SELECT p FROM Pdd p ORDER BY p.idPlanDesarrollo ASC")
    public List<Pdd> obtenerTodosOrdenadosASC();
	
}
