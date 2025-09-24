package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.Equipamento;

/**
 * 
 * @author sumset
 *
 */
@Transactional
@Repository
public interface IEquipamentoRepo extends PagingAndSortingRepository<Equipamento, Long>, Serializable  {

	/**
	 * 
	 * @param categoria
	 * @param sector
	 * @return
	 */
	@Query("SELECT eq FROM Equipamento eq WHERE eq.idLsSector.idArgumento= :sector AND eq.idLsCategoria.idArgumento= :categoria")
	public Equipamento validarSectorYCategoria(@Param("categoria")Long categoria,@Param("sector") Long sector);
}
