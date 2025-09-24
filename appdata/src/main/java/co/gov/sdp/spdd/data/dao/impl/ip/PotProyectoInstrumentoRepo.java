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
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotProyectoInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotProyectoInstrumentoRepo;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class PotProyectoInstrumentoRepo extends OperacionesBasicasFacade<PotProyectoInstrumento, Long>
		implements IPotProyectoInstrumentoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPotProyectoInstrumentoRepo potProyectoInstrumentoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<PotProyectoInstrumento, Long> getRepo() {
		return potProyectoInstrumentoRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PotProyectoInstrumento> cq = cb.createQuery(PotProyectoInstrumento.class);
		Root<PotProyectoInstrumento> potProyectoInstrumento = cq.from(PotProyectoInstrumento.class);
		List<Predicate> predicates = new ArrayList<>();
		if (potProyectoInstrumentoDTO.getIdPotInstrumento() != null) {
			predicates.add(cb.like(potProyectoInstrumento.get("idProyectoInstrumento").as(String.class),
					"%" + potProyectoInstrumentoDTO.getIdPotInstrumento() + "%"));
		}
		if (potProyectoInstrumentoDTO.getEstado() != null) {
			predicates.add(cb.like(potProyectoInstrumento.get("estado").as(String.class),
					"%" + potProyectoInstrumentoDTO.getEstado() + "%"));
		}
		if (potProyectoInstrumentoDTO.getNombreInstrumento() != null) {
			predicates.add(cb.like(potProyectoInstrumento.get("idPotInstrumento").get("codigo").as(String.class),
					"%" + potProyectoInstrumentoDTO.getNombreInstrumento() + "%"));
		}
		if (potProyectoInstrumentoDTO.getNombrePotProyecto() != null) {
			predicates.add(cb.like(potProyectoInstrumento.get("idPotProyecto").get("codigo").as(String.class),
					"%" + potProyectoInstrumentoDTO.getNombrePotProyecto() + "%"));
		}
		if (potProyectoInstrumentoDTO.getColumnaOrdenar().equals("estado")
				|| potProyectoInstrumentoDTO.getColumnaOrdenar().equals("idProyectoInstrumento"))
		{
			if (potProyectoInstrumentoDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(potProyectoInstrumento.get(potProyectoInstrumentoDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(potProyectoInstrumento.get(potProyectoInstrumentoDTO.getColumnaOrdenar())));
			}

		} else {
			if (potProyectoInstrumentoDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(potProyectoInstrumento.get(potProyectoInstrumentoDTO.getColumnaOrdenar())
						.get("codigo")));
			} else {
				cq.orderBy(cb.desc(potProyectoInstrumento.get(potProyectoInstrumentoDTO.getColumnaOrdenar())
						.get("codigo")));
			}
		}
		
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PotProyectoInstrumento.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public PotProyectoInstrumento buscarPorIdLsPotObraAndIdLsPotInstrumento(Long potInstrumento, Long potProyecto)
			throws SpddExceptions {
		return potProyectoInstrumentoRepository.findByIdLsPotObraAndIdLsPotInstrumento(potInstrumento, potProyecto);

	}

}
