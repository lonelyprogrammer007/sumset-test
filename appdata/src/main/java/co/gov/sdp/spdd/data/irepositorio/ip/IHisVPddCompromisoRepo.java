package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.ip.HisVPddCompromiso;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para hacer transacciones con la BD 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Transactional
@Repository
public interface IHisVPddCompromisoRepo extends CrudRepository<HisVPddCompromiso, Long>, Serializable {
	
}
