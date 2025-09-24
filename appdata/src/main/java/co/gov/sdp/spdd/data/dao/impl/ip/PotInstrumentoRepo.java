package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotInstrumentoRepo;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class PotInstrumentoRepo extends OperacionesBasicasFacade<PotInstrumento, Long> implements IPotInstrumentoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPotInstrumentoRepo potInstrumentoRepositorio;
	
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<PotInstrumento, Long> getRepo() {
		return potInstrumentoRepositorio;
	}

	@Override
	public FiltroDTO obtenerTodosFiltrado(PotInstrumentoDTO potInstrumentoDTO, Long inicio, Integer limite)	throws SpddExceptions {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PotInstrumento> cq = cb.createQuery(PotInstrumento.class);
		Root<PotInstrumento> potInstrumento = cq.from(PotInstrumento.class);
		List<Predicate> predicates = new ArrayList<>();
		if (potInstrumentoDTO.getIdInstrumentoPot() != null) {
			predicates.add(
					cb.equal(potInstrumento.get("idInstrumentoPot").as(String.class), potInstrumentoDTO.getIdInstrumentoPot()));
		}
		if(potInstrumentoDTO.getCodigoPotInstrumento() != null){
            predicates.add(cb.like(potInstrumento.get("codigo").as(String.class),
                    "%" + potInstrumentoDTO.getCodigoPotInstrumento() + "%"));
        }
		if(potInstrumentoDTO.getIdLsDenominacion() != null){
            predicates.add(cb.equal(potInstrumento.get("denominacion").get("idArgumento").as(String.class),
                    potInstrumentoDTO.getIdLsDenominacion()));
        }
		if(potInstrumentoDTO.getStringLsDenominacion() != null){
            predicates.add(cb.like(cb.lower(potInstrumento.get("denominacion").get("resultado").as(String.class)),
                    "%" + potInstrumentoDTO.getStringLsDenominacion().toLowerCase() + "%"));
        }
		if (potInstrumentoDTO.getIdPot() != null) {
			predicates.add(
					cb.equal(potInstrumento.get("idPot").get("idPot").as(String.class), potInstrumentoDTO.getIdPot()));
		}
		if(potInstrumentoDTO.getIdLsNivelInst() != null){
            predicates.add(cb.equal(potInstrumento.get("idLsNivelInst").get("idArgumento").as(String.class),
                    potInstrumentoDTO.getIdLsNivelInst()));
        }
		if(potInstrumentoDTO.getStringLsNivelInst() != null){
            predicates.add(cb.like(cb.lower(potInstrumento.get("idLsNivelInst").get("resultado").as(String.class)),
                    "%" + potInstrumentoDTO.getStringLsNivelInst().toLowerCase() + "%"));
        }
		
		cq.where(predicates.toArray(new Predicate[0]));

		if (potInstrumentoDTO.getColumnaOrdenar() != null) {
			/*
			if (potInstrumentoDTO.getTipoOrden().equals("asc")) {
				
			} else {
				
			}
			*/
		} else {
			cq.orderBy(cb.asc(potInstrumento.get("codigo")));
		}

		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PotInstrumento.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public PotInstrumento obtenerPorCodigoYIdPot(Long codigo, Long idPot) {
		return potInstrumentoRepositorio.obtenerPorCodigoYIdPot(codigo, idPot);
	}
	
	

}
