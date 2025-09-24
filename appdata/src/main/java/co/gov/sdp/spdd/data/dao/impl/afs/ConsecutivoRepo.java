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

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IConsecutivoRepo;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ConsecutivoRepo extends OperacionesBasicasFacade<Consecutivo, Long>
		implements IConsecutivoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IConsecutivoRepo consecutivoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<Consecutivo, Long> getRepo() {
		return consecutivoRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ConsecutivoDTO consecutivoDTO, Long inicio, Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Consecutivo> cq = cb.createQuery(Consecutivo.class);
		Root<Consecutivo> consecutivo = cq.from(Consecutivo.class);
		List<Predicate> predicates = new ArrayList<>();

		if (consecutivoDTO.getIdConsecutivo() != null) {
			predicates.add(cb.like(consecutivo.get("idConsecutivo").as(String.class),
					"%" + consecutivoDTO.getIdConsecutivo() + "%"));
		}
		if (consecutivoDTO.getDescripcion() != null) {
			predicates.add(cb.like(cb.lower(consecutivo.get("descripcion")),
					"%" + consecutivoDTO.getDescripcion().toLowerCase() + "%"));
		}
		if (consecutivoDTO.getCodigoEntidad() != null) {
			predicates.add(
					cb.like(cb.lower(consecutivo.get(NHSPDDConstantes.COD_ENTIDAD).get(NHSPDDConstantes.COD_ENTIDAD)),
							"%" + consecutivoDTO.getCodigoEntidad().toLowerCase() + "%"));
		}
		if (consecutivoDTO.getNombre() != null) {
			predicates.add(
					cb.like(cb.lower(consecutivo.get("nombre")), "%" + consecutivoDTO.getNombre().toLowerCase() + "%"));
		}
		if (consecutivoDTO.getSecuencia() != null) {
			predicates.add(
					cb.like(consecutivo.get("secuencia").as(String.class), "%" + consecutivoDTO.getSecuencia() + "%"));
		}
		if (consecutivoDTO.getVigencia() != null) {
			predicates.add(cb.like(cb.lower(consecutivo.get("vigencia")),
					"%" + consecutivoDTO.getVigencia().toLowerCase() + "%"));
		}
		if (consecutivoDTO.getIdPlanDesarrollo() != null) {
			predicates
					.add(cb.like(
							consecutivo.get(NHSPDDConstantes.ID_PLAN_DESARROLLO)
									.get(NHSPDDConstantes.ID_PLAN_DESARROLLO).as(String.class),
							"%" + consecutivoDTO.getIdPlanDesarrollo() + "%"));
		}
		if(consecutivoDTO.getColumnaOrdenar() != null)
		{
			if (consecutivoDTO.getColumnaOrdenar().equals(NHSPDDConstantes.ID_PLAN_DESARROLLO)
					|| consecutivoDTO.getColumnaOrdenar().equals("nombre")
					|| consecutivoDTO.getColumnaOrdenar().equals("secuencia")
					|| consecutivoDTO.getColumnaOrdenar().equals("descripcion")
					|| consecutivoDTO.getColumnaOrdenar().equals("vigencia")) {
				if (consecutivoDTO.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(consecutivo.get(consecutivoDTO.getColumnaOrdenar())));
				} else {
					cq.orderBy(cb.desc(consecutivo.get(consecutivoDTO.getColumnaOrdenar())));
				}
			} else {
				if (consecutivoDTO.getTipoOrden().equals("asc")) {
					cq.orderBy(
							cb.asc(consecutivo.get(consecutivoDTO.getColumnaOrdenar()).get(NHSPDDConstantes.COD_ENTIDAD)));
				} else {
					cq.orderBy(
							cb.desc(consecutivo.get(consecutivoDTO.getColumnaOrdenar()).get(NHSPDDConstantes.COD_ENTIDAD)));
				}
			}
		}
		else
		{
			cq.orderBy(cb.desc(consecutivo.get(NHSPDDConstantes.ID_PLAN_DESARROLLO)));
		}

		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(Consecutivo.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public Consecutivo obtenerConsecutivos(Long idPlan, String entidad, String nombre) throws SpddExceptions {
		return consecutivoRepository.obtenerConsecutivo(idPlan, entidad, nombre).get(0);
	}

}
