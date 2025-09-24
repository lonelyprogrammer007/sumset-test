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
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IFuncionarioClaveEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IFuncionarioClaveEntidadRepo;
import co.gov.sdp.spdd.data.model.afs.FuncionarioClaveEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class FuncionarioClaveEntidadRepo extends OperacionesBasicasFacade<FuncionarioClaveEntidad, Long>
		implements IFuncionarioClaveEntidadServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IFuncionarioClaveEntidadRepo funcionarioClaveEntidadRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<FuncionarioClaveEntidad, Long> getRepo() {
		return funcionarioClaveEntidadRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FuncionarioClaveEntidad> cq = cb.createQuery(FuncionarioClaveEntidad.class);
		Root<FuncionarioClaveEntidad> funcionarioClaveEntidad = cq.from(FuncionarioClaveEntidad.class);
		List<Predicate> predicates = new ArrayList<>();
		if (funcionarioClaveEntidadDTO.getIdFuncionarioEntidad() != null) {
			predicates.add(cb.like(funcionarioClaveEntidad.get("idFuncionarioEntidad").as(String.class),
					"%" + funcionarioClaveEntidadDTO.getIdFuncionarioEntidad() + "%"));
		}
		if (funcionarioClaveEntidadDTO.getCodigoEntidad() != null) {
			predicates.add(cb.equal(funcionarioClaveEntidad.get("codigoEntidad").get("codigoEntidad").as(String.class),
					funcionarioClaveEntidadDTO.getCodigoEntidad()));
		}
		if (funcionarioClaveEntidadDTO.getNombre() != null) {
			predicates.add(cb.like(cb.lower(funcionarioClaveEntidad.get("nombre")),
					"%" + funcionarioClaveEntidadDTO.getNombre().toLowerCase() + "%"));
		}
		if (funcionarioClaveEntidadDTO.getNombreIdLsFuncion() != null) {
			predicates.add(cb.like(
					cb.lower(funcionarioClaveEntidad.get("idLsFuncion").get(NHSPDDConstantes.RESULTADO)
							.as(String.class)),
					"%" + funcionarioClaveEntidadDTO.getNombreIdLsFuncion().toLowerCase() + "%"));
		}
		if (funcionarioClaveEntidadDTO.getNombreIdLsGenero() != null) {
			predicates.add(cb.like(
					cb.lower(
							funcionarioClaveEntidad.get("idLsGenero").get(NHSPDDConstantes.RESULTADO).as(String.class)),
					"%" + funcionarioClaveEntidadDTO.getNombreIdLsGenero().toLowerCase() + "%"));
		}
		if (funcionarioClaveEntidadDTO.getCargo() != null) {
			predicates.add(cb.like(cb.lower(funcionarioClaveEntidad.get("cargo")),
					"%" + funcionarioClaveEntidadDTO.getCargo().toLowerCase() + "%"));
		}
		if (funcionarioClaveEntidadDTO.getCorreo() != null) {
			predicates.add(cb.like(cb.lower(funcionarioClaveEntidad.get("correo")),
					"%" + funcionarioClaveEntidadDTO.getCorreo().toLowerCase() + "%"));
		}

		if (funcionarioClaveEntidadDTO.getColumnaOrdenar().equals("nombre")
				|| funcionarioClaveEntidadDTO.getColumnaOrdenar().equals("cargo")
				|| funcionarioClaveEntidadDTO.getColumnaOrdenar().equals("correo")
				|| funcionarioClaveEntidadDTO.getColumnaOrdenar().equals("idLsGenero")) {
			if (funcionarioClaveEntidadDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(funcionarioClaveEntidad.get(funcionarioClaveEntidadDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(funcionarioClaveEntidad.get(funcionarioClaveEntidadDTO.getColumnaOrdenar())));
			}

		} else {
			if (funcionarioClaveEntidadDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(funcionarioClaveEntidad.get(funcionarioClaveEntidadDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			} else {
				cq.orderBy(cb.desc(funcionarioClaveEntidad.get(funcionarioClaveEntidadDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(FuncionarioClaveEntidad.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public List<FuncionarioClaveEntidad> obtenerFuncionarioPorEntidad(String codigoEntidad) {
		return funcionarioClaveEntidadRepository.obtenerFuncionarioPorEntidad(codigoEntidad);
	}

	@Override
	public FuncionarioClaveEntidad validarFuncionarioPorIdLsFuncionYEntidad(String codigoEntidad, Long idLsFuncion)
			throws SpddExceptions {
		return funcionarioClaveEntidadRepository.validarFuncionarioPorIdLsFuncionYEntidad(codigoEntidad, idLsFuncion);
	}

}
