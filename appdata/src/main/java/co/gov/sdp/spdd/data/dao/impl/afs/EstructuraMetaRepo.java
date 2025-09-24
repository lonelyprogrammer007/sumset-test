package co.gov.sdp.spdd.data.dao.impl.afs;

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

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IEstructuraMetaRepo;
import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class EstructuraMetaRepo extends OperacionesBasicasFacade<EstructuraMeta, Long>
        implements IEstructuraMetaServiceRepo, Serializable {
	
	/**
	 * 
	 */
    @Autowired
    IEstructuraMetaRepo estructuraMetaRepository;
    
    /**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

    /**
     * 
     */
    @Override
    public CrudRepository<EstructuraMeta, Long> getRepo() {
        return estructuraMetaRepository;
    }
    
    /**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(EstructuraMetaDTO estructuraMetaDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EstructuraMeta> cq = cb.createQuery(EstructuraMeta.class);
		Root<EstructuraMeta> estructuraMeta = cq.from(EstructuraMeta.class);
		List<Predicate> predicates = new ArrayList<>();
		if (estructuraMetaDTO.getIdEstructuraMetas() != null) {
			predicates.add(cb.like(estructuraMeta.get("idEstructuraMetas").as(String.class), "%" + estructuraMetaDTO.getIdEstructuraMetas() + "%"));
		}
		if (estructuraMetaDTO.getIdLsVerbo() != null) {
			predicates.add(cb.like(estructuraMeta.get("idLsVerbo").get(NHSPDDConstantes.RESULTADO), "%" + estructuraMetaDTO.getIdLsVerbo() + "%"));
		}
		if (estructuraMetaDTO.getNombreUnidadMedida()!= null) {
			predicates.add(cb.like(cb.lower(estructuraMeta.get("idLsUnidadMedida").get(NHSPDDConstantes.RESULTADO)), "%" + estructuraMetaDTO.getNombreUnidadMedida().toLowerCase() + "%"));
		}
		if (estructuraMetaDTO.getNombreVerbo() != null) {
			predicates.add(cb.like(cb.lower(estructuraMeta.get("idLsVerbo").get(NHSPDDConstantes.RESULTADO)), "%" + estructuraMetaDTO.getNombreVerbo().toLowerCase() + "%"));
		}
		if (estructuraMetaDTO.getTipo() != null) {
			predicates.add(cb.like(estructuraMeta.get("tipo").as(String.class), "%" + estructuraMetaDTO.getTipo() + "%"));
		}
		if (estructuraMetaDTO.getEstado() != null) {
			predicates.add(cb.like(estructuraMeta.get("estado").as(String.class), "%" + estructuraMetaDTO.getEstado() + "%"));
		}
		if (estructuraMetaDTO.getColumnaOrdenar().equals("estado")
				|| estructuraMetaDTO.getColumnaOrdenar().equals("idEstructuraMetas") ||
				estructuraMetaDTO.getColumnaOrdenar().equals("tipo")) {
			if (estructuraMetaDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(estructuraMeta.get(estructuraMetaDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(estructuraMeta.get(estructuraMetaDTO.getColumnaOrdenar())));
			}

		} else {
			if (estructuraMetaDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(estructuraMeta.get(estructuraMetaDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			} else {
				cq.orderBy(cb.desc(estructuraMeta.get(estructuraMetaDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(EstructuraMeta.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public EstructuraMeta validarUnidadMedidaYVerbo(Long unidadMedida, Long verbo) throws SpddExceptions {
		return estructuraMetaRepository.validarUnidadMedidaYVerbo(unidadMedida, verbo);
	}
}
