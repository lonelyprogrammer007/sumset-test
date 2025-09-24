package co.gov.sdp.spdd.data.irepositorio.bp;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.view.VistaBpProyectoInversion;

@Transactional
@Repository
public interface IVistaBpProyectoInversionRepo extends CrudRepository<VistaBpProyectoInversion, Long> {

}
