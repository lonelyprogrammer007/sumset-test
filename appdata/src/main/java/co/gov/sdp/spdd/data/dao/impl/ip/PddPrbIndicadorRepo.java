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
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPrbIndicadorServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddPrbIndicadorRepo;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase servicio de PddPrbIndicador
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddPrbIndicadorRepo extends OperacionesBasicasFacade<PddPrbIndicador, Long>
		implements IPddPrbIndicadorServiceRepo, Serializable {

	@Autowired
	IPddPrbIndicadorRepo prbIndicadorRepositorio;
	
	@PersistenceContext
	transient EntityManager em;

	@Override
	public CrudRepository<PddPrbIndicador, Long> getRepo() {

		return prbIndicadorRepositorio;
	}

	@Override
	public List<PddPrbIndicador> obtenerPrbIndicadorPorProblematica(Long id) throws SpddExceptions {
		return prbIndicadorRepositorio.buscarPorProblematica(id);
	}

	@Override
	public PddPrbIndicador consultarPorIndicidicadorYProblematica(Long idIndicador, Long idProblematica) {

		return prbIndicadorRepositorio.buscarPorIndicadorYProblematica(idIndicador, idProblematica);
	}

	@Override
	public FiltroDTO obtenerTodosFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddPrbIndicador> cq = cb.createQuery(PddPrbIndicador.class);
		Root<PddPrbIndicador> pddPrbIndicador = cq.from(PddPrbIndicador.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (pddPrbIndicadorDTO.getIdProbInd() != null) {
			predicates.add(
					cb.like(pddPrbIndicador.get("idProbInd").as(String.class), "%" + pddPrbIndicadorDTO.getIdProbInd() + "%"));
		}
		
		if (pddPrbIndicadorDTO.getIdIndicador() != null) {
			predicates.add(
					cb.like(pddPrbIndicador.get("idIndicador").get("idIndicador").as(String.class), "%" + pddPrbIndicadorDTO.getIdIndicador() + "%"));
		}
		
		if (pddPrbIndicadorDTO.getIdProblematica() != null) {
			predicates.add(
					cb.equal(pddPrbIndicador.get("idProblematica").get("idProblematica").as(String.class), pddPrbIndicadorDTO.getIdProblematica()));
		}
		
		if(pddPrbIndicadorDTO.getNombre() != null && !"".equals(pddPrbIndicadorDTO.getNombre()))
		{
			predicates.add(
					cb.like(cb.lower(pddPrbIndicador.get("idIndicador").get("nombre").as(String.class)), "%" + pddPrbIndicadorDTO.getNombre().toLowerCase() + "%"));
		}
		
		if(pddPrbIndicadorDTO.getTipo() != null && !"".equals(pddPrbIndicadorDTO.getTipo()))
		{
			predicates.add(
					cb.like(cb.lower(pddPrbIndicador.get("idIndicador").get("tipo").as(String.class)), "%" + pddPrbIndicadorDTO.getTipo().toLowerCase() + "%"));
		}
		
		if(pddPrbIndicadorDTO.getLineaBase() != null )
		{
			predicates.add(
					cb.like(pddPrbIndicador.get("idIndicador").get("lineaBase").as(String.class), "%" + pddPrbIndicadorDTO.getLineaBase() + "%"));
		}
		
		if(pddPrbIndicadorDTO.getFuente() != null && !"".equals(pddPrbIndicadorDTO.getFuente()))
		{
			predicates.add(
					cb.like(cb.lower(pddPrbIndicador.get("idIndicador").get("fuente").as(String.class)), "%" + pddPrbIndicadorDTO.getFuente().toLowerCase() + "%"));
		}
		
		if(pddPrbIndicadorDTO.getYearLineaBase() != null && !"".equals(pddPrbIndicadorDTO.getYearLineaBase()))
		{
			predicates.add(
					cb.like(cb.lower(pddPrbIndicador.get("idIndicador").get("yearLineaBase").as(String.class)), "%" + pddPrbIndicadorDTO.getYearLineaBase().toLowerCase() + "%"));
		}
		
		if(pddPrbIndicadorDTO.getInformacionSoporte() != null && !"".equals(pddPrbIndicadorDTO.getInformacionSoporte()))
		{
			predicates.add(
					cb.like(cb.lower(pddPrbIndicador.get("idIndicador").get("informacionSoporte").as(String.class)), "%" + pddPrbIndicadorDTO.getInformacionSoporte().toLowerCase() + "%"));
		}
		
		cq.where(predicates.toArray(new Predicate[0]));
		
		if (pddPrbIndicadorDTO.getColumnaOrdenar() != null) {
			if (pddPrbIndicadorDTO.getTipoOrden().equals("asc")) {
				if (pddPrbIndicadorDTO.getColumnaOrdenar().equalsIgnoreCase("idProbInd")) {
					cq.orderBy(cb.asc(pddPrbIndicador.get("idProbInd")));
				} else {					
					cq.orderBy(cb.asc(pddPrbIndicador.get("idIndicador").get(pddPrbIndicadorDTO.getColumnaOrdenar())));					
				}
			} else {
				if (pddPrbIndicadorDTO.getColumnaOrdenar().equalsIgnoreCase("idProbInd")) {
					cq.orderBy(cb.desc(pddPrbIndicador.get("idProbInd")));
				} else {					
					cq.orderBy(cb.desc(pddPrbIndicador.get("idIndicador").get(pddPrbIndicadorDTO.getColumnaOrdenar())));					
				}
			}
		} else {
			cq.orderBy(cb.asc(pddPrbIndicador.get("idProbInd")));
		}		
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddPrbIndicador.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;		
	}

}
