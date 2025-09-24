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

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IEquipamentoRepo;
import co.gov.sdp.spdd.data.model.afs.Equipamento;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class EquipamentoRepo extends OperacionesBasicasFacade<Equipamento, Long> implements IEquipamientoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IEquipamentoRepo equipamentoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<Equipamento, Long> getRepo() {
		return equipamentoRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(EquipamientoDTO equipamientoDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Equipamento> cq = cb.createQuery(Equipamento.class);
		Root<Equipamento> equipamento = cq.from(Equipamento.class);
		List<Predicate> predicates = new ArrayList<>();
		if (equipamientoDTO.getIdEquipamento() != null) {
			predicates.add(cb.like(equipamento.get("idEquipamento").as(String.class), "%" + equipamientoDTO.getIdEquipamento() + "%"));
		}
		if (equipamientoDTO.getNombre() != null) {
			predicates.add(cb.like(cb.lower(equipamento.get("nombre")), "%" + equipamientoDTO.getNombre().toLowerCase() + "%"));
		}
		if (equipamientoDTO.getDescripcion() != null) {
			predicates.add(cb.like(cb.lower(equipamento.get("descripcion")), "%" + equipamientoDTO.getDescripcion().toLowerCase() + "%"));
		}
		if (equipamientoDTO.getEstado() != null) {
			predicates.add(cb.like(equipamento.get("estado").as(String.class), "%" + equipamientoDTO.getEstado() + "%"));
		}
		if(equipamientoDTO.getNombreSector() != null) {
			predicates.add(cb.like(cb.lower(equipamento.get("idLsSector").get(NHSPDDConstantes.RESULTADO).as(String.class)), "%" + equipamientoDTO.getNombreSector().toLowerCase() + "%"));
		}
		if(equipamientoDTO.getNombreCategoria()!=null) {
			predicates.add(cb.like(cb.lower(equipamento.get("idLsCategoria").get(NHSPDDConstantes.RESULTADO).as(String.class)), "%" + equipamientoDTO.getNombreCategoria().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		if (equipamientoDTO.getColumnaOrdenar().equals("estado") || 
				equipamientoDTO.getColumnaOrdenar().equals("idEquipamento")) {
			if (equipamientoDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(equipamento.get(equipamientoDTO.getColumnaOrdenar())));
			}
			else {
				cq.orderBy(cb.desc(equipamento.get(equipamientoDTO.getColumnaOrdenar())));
			}
		} else {
			if (equipamientoDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(equipamento.get(equipamientoDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			}
			 else {
					cq.orderBy(cb.desc(equipamento.get(equipamientoDTO.getColumnaOrdenar())
							.get(NHSPDDConstantes.RESULTADO)));
				}
	}
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(Equipamento.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public Equipamento validarSectorYCategoria(Long sector, Long categoria) {
		
		return equipamentoRepository.validarSectorYCategoria(categoria, sector);
	}

}
