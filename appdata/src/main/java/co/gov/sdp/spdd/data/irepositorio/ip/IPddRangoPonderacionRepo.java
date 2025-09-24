package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.model.ip.PddRangoPonderacion;

@Transactional
@Repository
public interface IPddRangoPonderacionRepo extends CrudRepository<PddRangoPonderacion, Long>, Serializable{

    /**
     * Metodo que permite consultar los PddRangoPonderacion de un plan de desarrollo en especifico
     * @param idPdd Long que representa el identificador del plan de desarrollo por el cual se quiere hacer la busqueda
     * @return una lista de PddRangoPonderacion correspondiente a la busqueda o null en caso contrario
     */
    @Query("SELECT p FROM PddRangoPonderacion p WHERE p.idPlanDesarrollo.idPlanDesarrollo= :pdd ORDER BY p.rango DESC")
	public List<PddRangoPonderacion> obtenerPddRangoPonderacionPorIdPdd(@Param("pdd") Long idPdd);
	
}
