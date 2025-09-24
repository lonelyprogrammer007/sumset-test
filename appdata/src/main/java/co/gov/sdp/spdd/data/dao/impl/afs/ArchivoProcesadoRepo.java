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

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IArchivoProcesadoRepo;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;
import co.gov.sdp.spdd.data.utils.QueryUtils;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ArchivoProcesadoRepo extends OperacionesBasicasFacade<ArchivoProcesado, Long>
		implements IArchivoProcesadoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IArchivoProcesadoRepo archivoProcesadoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ArchivoProcesado, Long> getRepo() {
		return archivoProcesadoRepository;
	}
	
	

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ArchivoProcesadoDTO archivoProcesadoDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ArchivoProcesado> cq = cb.createQuery(ArchivoProcesado.class);
		Root<ArchivoProcesado> archivoProcesado = cq.from(ArchivoProcesado.class);
		List<Predicate> predicates = new ArrayList<>();
		if (archivoProcesadoDTO.getIdArchivo() != null) {
			predicates.add(cb.like(archivoProcesado.get("idArchivo").as(String.class),
					"%" + archivoProcesadoDTO.getIdArchivo() + "%"));
		}
		if (archivoProcesadoDTO.getEstado() != null) {
			predicates.add(cb.like(archivoProcesado.get("estado"), "%" + archivoProcesadoDTO.getEstado() + "%"));
		}
		if (archivoProcesadoDTO.getDetalle() != null) {
			predicates.add(cb.like(archivoProcesado.get("detalle"), "%" + archivoProcesadoDTO.getDetalle() + "%"));
		}
		if (archivoProcesadoDTO.getFechaCargue() != null && !archivoProcesadoDTO.getFechaCargue().equals("")) {
			Predicate p = QueryUtils.filtroFechas("fechaCargue", archivoProcesadoDTO.getFechaCargue(),
					NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, archivoProcesado);
			predicates.add(p);

		}
		if (archivoProcesadoDTO.getCodigoUsuario() != null) {
			predicates.add(
					cb.like(archivoProcesado.get("codigoUsuario"), "%" + archivoProcesadoDTO.getCodigoUsuario() + "%"));
		}
		if (archivoProcesadoDTO.getNombreArchivo() != null) {
			predicates.add(
					cb.like(archivoProcesado.get("nombreArchivo"), "%" + archivoProcesadoDTO.getNombreArchivo() + "%"));
		}
		if (archivoProcesadoDTO.getIdConfigCargue() != null) {
		predicates.add(cb.like(archivoProcesado.get(NHSPDDConstantes.ID_CONFIG_CARGUE).get(NHSPDDConstantes.ID_CONFIG_CARGUE).as(String.class),
					"%" + archivoProcesadoDTO.getIdConfigCargue() + "%"));
		}
		if (archivoProcesadoDTO.getNombreTema() != null) {
			predicates.add(
					cb.like(archivoProcesado.get(NHSPDDConstantes.ID_CONFIG_CARGUE).get("idLsTema").get("resultado").as(String.class),
							"%" + archivoProcesadoDTO.getNombreTema() + "%"));
		}
		if (archivoProcesadoDTO.getNombreModulo() != null) {
			predicates.add(
					cb.like(archivoProcesado.get("idConfigCargue").get("idLsModulo").get("resultado").as(String.class),
							"%" + archivoProcesadoDTO.getNombreModulo() + "%"));
		}

		if (archivoProcesadoDTO.getColumnaOrdenar().equals("idArchivo")
				|| archivoProcesadoDTO.getColumnaOrdenar().equals("fechaCargue")) {
			if (archivoProcesadoDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(archivoProcesado.get(archivoProcesadoDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(archivoProcesado.get(archivoProcesadoDTO.getColumnaOrdenar())));
			}

		} else {
			if (archivoProcesadoDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(archivoProcesado.get(NHSPDDConstantes.ID_CONFIG_CARGUE).get(archivoProcesadoDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			} else {
				cq.orderBy(cb.desc(archivoProcesado.get(NHSPDDConstantes.ID_CONFIG_CARGUE).get(archivoProcesadoDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ArchivoProcesado.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}
}
