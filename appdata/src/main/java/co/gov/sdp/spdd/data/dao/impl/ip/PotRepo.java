package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotRepo;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;
import co.gov.sdp.spdd.data.utils.QueryUtils;

@Service
public class PotRepo extends OperacionesBasicasFacade<Pot, Long> implements IPotServiceRepo, Serializable {

	@Autowired
	IPotRepo potRepo;

	@Autowired
	transient EntityManager em;

	@Override
	public CrudRepository<Pot, Long> getRepo() {

		return potRepo;
	}

	@Override
	public FiltroDTO filtrarPorCampo(PotDTO peticion, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pot> cq = cb.createQuery(Pot.class);
		Root<Pot> componenteRoot = cq.from(Pot.class);
		List<Predicate> predicates = new ArrayList<>();

		if (peticion.getCodigoPot() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("codigoPot").as(String.class)),
					"%" + peticion.getCodigoPot() + "%"));
		}

		if (peticion.getNombreAdoptado() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idLsAdoptado").get("resultado").as(String.class)),
					"%" + peticion.getNombreAdoptado() + "%"));
		}
		if (peticion.getActoAdministrativo() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("actoAdministrativo").as(String.class)),
					"%" + peticion.getActoAdministrativo() + "%"));
		}
		if (peticion.getYearInicio() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("yearInicio").as(String.class)),
					"%" + peticion.getYearInicio() + "%"));
		}
		if (peticion.getYearFin() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("yearFin").as(String.class)),
					"%" + peticion.getYearFin() + "%"));
		}
		if (peticion.getFecha() != null && !peticion.getFecha().equals("")) {
			Predicate p = QueryUtils.filtroFechas("fecha", peticion.getFecha(), NHSPDDConstantes.FORMATO_FECHA_FILTRO,
					cb, componenteRoot);
			predicates.add(p);
		}
		if (peticion.getEstado() != null) {
			predicates.add(cb.like(componenteRoot.get("estado").as(String.class), "%" + peticion.getEstado() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		if (peticion.getColumnaOrdenar() != null) {
			if (!peticion.getColumnaOrdenar().equals("nombreAdoptado")) {
				if (peticion.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(componenteRoot.get(peticion.getColumnaOrdenar())));
				} else {
					cq.orderBy(cb.desc(componenteRoot.get(peticion.getColumnaOrdenar())));
				}
			} else {
				if (peticion.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(componenteRoot.get("idLsAdoptado").get("resultado")));
				} else {
					cq.orderBy(cb.desc(componenteRoot.get("idLsAdoptado").get("resultado")));
				}
			}
		} else {
			cq.orderBy(cb.desc(componenteRoot.get("idPot")));
		}
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(Pot.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public Pot obtenerPorCodigo(String codigoPot) {
		return potRepo.obtenerPorCodigo(codigoPot);
	}

	@Override
	public List<Pot> obtenerTodosPorEstado(String estado) {
		return potRepo.obtenerPorEstado(estado);
	}

}
