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
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla Pdl de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 22/04/2020
 */
@Transactional
@Repository
public interface IPdlRepo extends CrudRepository<Pdl, Long>, Serializable {
	
	/**
     * Metodo que permite consultar los PDL de un estado en especifico
     * @param idEstado Long que representa el identificador del estado por el cual se quiere hacer la busqueda
     * @return una lista de entidades PDL correspondientes a la busqueda o null en caso contrario
     */
    @Query("SELECT p FROM Pdl p WHERE p.idLsEstadoPlan.idArgumento=:estado")
    public List<Pdl> obtenerPorEstado(@Param("estado") Long idEstado);
    
    /**
     * Metodo que permite consultar los PDL de una entidad
     * @param codigoEntidad Long que representa el codigo de la entidad por el cual se quiere hacer la busqueda
     * @return una lista de entidades PDL correspondientes a la busqueda o null en caso contrario
     */
    @Query("SELECT p FROM Pdl p WHERE LOWER(p.idLsEstadoPlan.resultado)= LOWER(:result) AND p.codigoEntidad.codigoEntidad=:codigo")
    public List<Pdl> obtenerPorLsEstadoPlanYCodigoEntidad(@Param("result") String resultado, @Param("codigo") String codigoEntidad);
    
}
