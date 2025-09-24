package co.gov.sdp.spdd.data.dao.impl.afs;

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

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfiguracionNotificacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IConfiguracionNotificacionRepo;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class ConfiguracionNotificacionRepo extends OperacionesBasicasFacade<ConfiguracionNotificacion, Long>
		implements IConfiguracionNotificacionServiceRepo {

	/**
	 * 
	 */
	@Autowired
	IConfiguracionNotificacionRepo configNotificacionRepo;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ConfiguracionNotificacion, Long> getRepo() {
		return configNotificacionRepo;
	}

	/**
	 * 
	 * @param componente
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ConfiguracionNotificacionDTO configuracionNotificacionDTO, Long inicio,
			Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfiguracionNotificacion> cq = cb.createQuery(ConfiguracionNotificacion.class);
		Root<ConfiguracionNotificacion> configuracionNotificacion = cq.from(ConfiguracionNotificacion.class);
		List<Predicate> predicates = new ArrayList<>();
		if (configuracionNotificacionDTO.getIdConfigNotificacion() != null) {
			predicates.add(cb.like(configuracionNotificacion.get("idConfigNotificacion").as(String.class),
					"%" + configuracionNotificacionDTO.getIdConfigNotificacion() + "%"));
		}
		if (configuracionNotificacionDTO.getAsunto() != null) {
			predicates.add(cb.like(cb.lower(configuracionNotificacion.get("asunto")),
					"%" + configuracionNotificacionDTO.getAsunto().toLowerCase() + "%"));
		}
		if (configuracionNotificacionDTO.getMensaje() != null) {
			predicates.add(cb.like(cb.lower(configuracionNotificacion.get("mensaje")),
					"%" + configuracionNotificacionDTO.getMensaje().toLowerCase() + "%"));
		}
		if (configuracionNotificacionDTO.getOperacionOrigen() != null) {
			predicates.add(cb.like(cb.lower(configuracionNotificacion.get("operacionOrigen")),
					"%" + configuracionNotificacionDTO.getOperacionOrigen().toLowerCase() + "%"));
		}
		if (configuracionNotificacionDTO.getRequiereAccion() != null) {
			predicates.add(cb.like(configuracionNotificacion.get("requiereAccion").as(String.class),
					"%" + configuracionNotificacionDTO.getRequiereAccion() + "%"));
		}

		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ConfiguracionNotificacion.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

}
