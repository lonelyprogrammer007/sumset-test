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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IHisVPddCompromisoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IHisVPddCompromisoRepo;
import co.gov.sdp.spdd.data.model.ip.HisVPddCompromiso;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository.
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Service
public class HisVPddCompromisoRepo extends OperacionesBasicasFacade<HisVPddCompromiso, Long> implements IHisVPddCompromisoServiceRepo, Serializable {
	
	/**
	 * Inyeccion de IHisVPddCompromisoRepo
	 */
	@Autowired
	IHisVPddCompromisoRepo hisVPddCompromisoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	/**
	 * Metodo que permite retornar el Reposity correspondiente a la entidad hisVPddCompromiso
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad hisVPddCompromiso
	 */
	@Override
	public CrudRepository<HisVPddCompromiso, Long> getRepo() {
		return hisVPddCompromisoRepository;
	}

	@Override
	public FiltroDTO filtrarPorCampo(HisVPddCompromisoDTO hisVPddCompromisoDTO, Long inicio, Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<HisVPddCompromiso> cq = cb.createQuery(HisVPddCompromiso.class);
		Root<HisVPddCompromiso> hisVPddCompromiso = cq.from(HisVPddCompromiso.class);
		List<Predicate> predicates = new ArrayList<>();
		if (hisVPddCompromisoDTO.getIdCompromiso() != null) {
			predicates.add(cb.like(hisVPddCompromiso.get("idCompromiso").as(String.class),
					"%" + hisVPddCompromisoDTO.getIdCompromiso() + "%"));
		}
		if (hisVPddCompromisoDTO.getIdHisPlanDesarrollo() != null) {
			predicates.add(
					cb.like(hisVPddCompromiso.get("idHisPlanDesarrollo").get("idHisPlanDesarrollo").as(String.class),
							"%" + hisVPddCompromisoDTO.getIdHisPlanDesarrollo() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(HisVPddCompromiso.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

}
