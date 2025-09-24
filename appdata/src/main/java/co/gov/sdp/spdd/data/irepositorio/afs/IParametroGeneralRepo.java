package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;

@Transactional
@Repository
public interface IParametroGeneralRepo extends CrudRepository<ParametroGeneral, String>, Serializable  {

}
