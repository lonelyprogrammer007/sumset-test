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
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IParametroGeneralServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IParametroGeneralRepo;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ParametroGeneralRepo extends OperacionesBasicasFacade<ParametroGeneral, String>
		implements IParametroGeneralServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IParametroGeneralRepo parametroGeneralRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ParametroGeneral, String> getRepo() {
		return parametroGeneralRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ParametroGeneralDTO parametroGeneralDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ParametroGeneral> cq = cb.createQuery(ParametroGeneral.class);
		Root<ParametroGeneral> parametroGeneral = cq.from(ParametroGeneral.class);
		List<Predicate> predicates = new ArrayList<>();
	
		if (parametroGeneralDTO.getCodigoParametro() != null) {
			predicates.add(
					cb.like(parametroGeneral.get("codigoParametro"), "%" + parametroGeneralDTO.getCodigoParametro()));
		}
		if (parametroGeneralDTO.getArgumento() != null) {
			predicates.add(cb.like(cb.lower(parametroGeneral.get(NHSPDDConstantes.ARGUMENTO)), "%" + parametroGeneralDTO.getArgumento().toLowerCase()+ "%"));
		}
		if (parametroGeneralDTO.getDescripcion() != null) {
			predicates.add(
					cb.like(cb.lower(parametroGeneral.get("descripcion")), "%" + parametroGeneralDTO.getDescripcion().toLowerCase() + "%"));
		}
		if (parametroGeneralDTO.getFechaCreacion() != null) {
			predicates.add(
					cb.like(parametroGeneral.get("fechaCreacion"), "%" + parametroGeneralDTO.getFechaCreacion() + "%"));
		}
		if (parametroGeneralDTO.getFechaModificacion() != null) {
			predicates.add(cb.like(parametroGeneral.get("fechaModificacion"),
					"%" + parametroGeneralDTO.getFechaModificacion() + "%"));
		}
		if (parametroGeneralDTO.getNombre() != null) {
			predicates.add(cb.like(cb.lower(parametroGeneral.get("nombre")), "%" + parametroGeneralDTO.getNombre().toLowerCase() + "%"));
		}
		if (parametroGeneralDTO.getUsuarioCreacion() != null) {
			predicates.add(cb.like(cb.lower(parametroGeneral.get("usuarioCreacion")),
					"%" + parametroGeneralDTO.getUsuarioCreacion().toLowerCase() + "%"));
		}
		if (parametroGeneralDTO.getUsuarioModificacion() != null) {
			predicates.add(cb.like(cb.lower(parametroGeneral.get("usuarioModificacion")),
					"%" + parametroGeneralDTO.getUsuarioModificacion().toLowerCase() + "%"));
		}
		
		if (parametroGeneralDTO.getColumnaOrdenar().equals("codigoParametro")
				|| parametroGeneralDTO.getColumnaOrdenar().equals("nombre") ||
				parametroGeneralDTO.getColumnaOrdenar().equals("descripcion")
				||parametroGeneralDTO.getColumnaOrdenar().equals("argumento") ) {
			if (parametroGeneralDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(parametroGeneral.get(parametroGeneralDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(parametroGeneral.get(parametroGeneralDTO.getColumnaOrdenar())));
			}

		} 
		
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ParametroGeneral.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}
}
