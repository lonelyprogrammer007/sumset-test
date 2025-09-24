package co.gov.sdp.spdd.data.irepositorio.bp;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvEspecif de la BD
 * 
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Transactional
@Repository
public interface IBpProyInvEspecifRepo extends CrudRepository<BpProyInvEspecif, Long> {

}
