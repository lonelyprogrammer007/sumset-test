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
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.ip.ICompromisoEstrategicoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.ICompromisoEstrategicoRepo;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad ComrpomisoEstrategico
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Service
public class CompromisoEstrategicoRepo extends OperacionesBasicasFacade<CompromisoEstrategico, Long>
		implements ICompromisoEstrategicoServiceRepo, Serializable {

	/**
	 * Inyeccion de ICompromisoEstrategicoRepo
	 */
	@Autowired
	ICompromisoEstrategicoRepo compromisoEstrategicoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * Metodo que permite retornar el Reposity correspondiente a la entidad
	 * CompromisoEstrategico
	 * 
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad
	 *         CompromisoEstrategico
	 */
	@Override
	public CrudRepository<CompromisoEstrategico, Long> getRepo() {
		return compromisoEstrategicoRepository;
	}

	@Override
	public FiltroDTO filtrarPorCampo(CompromisoEstrategicoDTO compromisoEstrategicoDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CompromisoEstrategico> cq = cb.createQuery(CompromisoEstrategico.class);
		Root<CompromisoEstrategico> compromisoEstrategico = cq.from(CompromisoEstrategico.class);
		List<Predicate> predicates = new ArrayList<>();
		if (compromisoEstrategicoDTO.getIdCompromiso() != null) {
			predicates.add(cb.like(compromisoEstrategico.get("idCompromiso").as(String.class),
					"%" + compromisoEstrategicoDTO.getIdCompromiso() + "%"));
		}
		if (compromisoEstrategicoDTO.getNombreTematica() != null) {
			predicates
					.add(cb.like(cb.lower(compromisoEstrategico.get("idLsTematica").get("resultado").as(String.class)),
							"%" + compromisoEstrategicoDTO.getNombreTematica().toLowerCase() + "%"));
		}
		if (compromisoEstrategicoDTO.getNombreCompromisoEstrategico() != null) {
			predicates.add(
					cb.like(cb.lower(compromisoEstrategico.get("idLsEstrategico").get("resultado").as(String.class)),
							"%" + compromisoEstrategicoDTO.getNombreCompromisoEstrategico().toLowerCase() + "%"));
		}
		if (compromisoEstrategicoDTO.getEstado() != null) {
			predicates.add(cb.like(compromisoEstrategico.get("estado").as(String.class),
					"%" + compromisoEstrategicoDTO.getEstado() + "%"));
		}
		
		
		if(compromisoEstrategicoDTO.getColumnaOrdenar() != null) {
			
			if (compromisoEstrategicoDTO.getColumnaOrdenar().equals("idCompromiso")	|| compromisoEstrategicoDTO.getColumnaOrdenar().equals("estado")) {
				if (compromisoEstrategicoDTO.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(compromisoEstrategico.get(compromisoEstrategicoDTO.getColumnaOrdenar())));
				} else {
					cq.orderBy(cb.desc(compromisoEstrategico.get(compromisoEstrategicoDTO.getColumnaOrdenar())));

				}
			} else {
				if (compromisoEstrategicoDTO.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(compromisoEstrategico.get(compromisoEstrategicoDTO.getColumnaOrdenar())
							.get(NHSPDDConstantes.RESULTADO)));
				} else {
					cq.orderBy(cb.desc(compromisoEstrategico.get(compromisoEstrategicoDTO.getColumnaOrdenar())
							.get(NHSPDDConstantes.RESULTADO)));

				}
			}
			
		}else {
			cq.orderBy(cb.asc(compromisoEstrategico.get("idCompromiso")));
		}
		
		

		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(CompromisoEstrategico.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public CompromisoEstrategico buscarPorIdTematicaYIdCompromisoEstrategico(Long idTematica,
			Long idCompromisoEstrategico) throws SpddExceptions {
		return compromisoEstrategicoRepository.obtenerPorIdTematicaYIdCompromisoEstrategico(idTematica,
				idCompromisoEstrategico);
	}

}
