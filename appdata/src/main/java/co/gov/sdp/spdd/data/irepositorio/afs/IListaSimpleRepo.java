package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.afs.ListaSimple;

/**
 * Interface que tiene los metodos de crud de una bd
 *
 * @author Bryan Munoz
 *
 */
@Transactional
@Repository
public interface IListaSimpleRepo extends PagingAndSortingRepository<ListaSimple, Long>, Serializable  {
	
}
