package co.gov.sdp.spdd.data.irepositorio.bp;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyectoInversion de la BD
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Transactional
@Repository
public interface IBpProyectoInversionRepo extends CrudRepository<BpProyectoInversion, Long>, Serializable {
	 
}
