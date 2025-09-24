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

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IUsuarioEntidadRepo;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class UsuarioEntidadRepo extends OperacionesBasicasFacade<UsuarioEntidad, Long>
        implements IUsuarioEntidadServiceRepo, Serializable {

	/*
	 * 
	 */
    @Autowired
    IUsuarioEntidadRepo usuarioEntidadRepository;

    /**
     * 
     */
    @PersistenceContext
	transient EntityManager em;
    
    /**
     * 
     */
    @Override
    public CrudRepository<UsuarioEntidad, Long> getRepo() {
        return usuarioEntidadRepository;
    }
    
    @Override
	public FiltroDTO filtrarPorCampo(UsuarioEntidadDTO usuarioEntidadDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioEntidad> cq = cb.createQuery(UsuarioEntidad.class);
		Root<UsuarioEntidad> usuarioEntidad = cq.from(UsuarioEntidad.class);
		List<Predicate> predicates = new ArrayList<>();
		if (usuarioEntidadDTO.getIdUsuarioEntidad() != null) {
			predicates.add(cb.like(usuarioEntidad.get("idUsuarioEntidad").as(String.class),
					"%" + usuarioEntidadDTO.getIdUsuarioEntidad() + "%"));
		}
		if (usuarioEntidadDTO.getCodigoUsuario() != null) {
			predicates.add(cb.like(usuarioEntidad.get("usuario"),
					"%" + usuarioEntidadDTO.getCodigoUsuario() + "%"));
		}
		if (usuarioEntidadDTO.getCodigoEntidad() != null) {
			predicates.add(cb.like(usuarioEntidad.get("codigoEntidad").get("codigoEntidad").as(String.class),
					"%" + usuarioEntidadDTO.getCodigoEntidad() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(UsuarioEntidad.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<UsuarioEntidad> obtenerPorUsuario(String usuario) {
		return usuarioEntidadRepository.obtenerPorUsuario(usuario);
	}

}
