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
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.ILineaDeInversionRepo;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;
import co.gov.sdp.spdd.data.utils.QueryUtils;

@Transactional
@Service
public class LineaDeInversionRepo extends OperacionesBasicasFacade<LineaDeInversion, Long>
		implements ILineaDeInversionServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	ILineaDeInversionRepo inversionRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<LineaDeInversion, Long> getRepo() {
		return inversionRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(LineaDeInversionDTO lineaDeInversionDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LineaDeInversion> cq = cb.createQuery(LineaDeInversion.class);
		Root<LineaDeInversion> lineaDeInversion = cq.from(LineaDeInversion.class);
		List<Predicate> predicates = new ArrayList<>();
		if (lineaDeInversionDTO.getIdLineaInversion() != null) {

			predicates.add(cb.like(lineaDeInversion.get("idLineaInversion").as(String.class),
					"%" + lineaDeInversionDTO.getIdLineaInversion()));
		}
		if (lineaDeInversionDTO.getConcepto() != null) {
			predicates.add(cb.like(cb.lower(lineaDeInversion.get("concepto")),
					"%" + lineaDeInversionDTO.getConcepto().toLowerCase() + "%"));
		}
		if (lineaDeInversionDTO.getEstablecido() != null) {
			predicates.add(cb.like(cb.lower(lineaDeInversion.get("establecido")),
					"%" + lineaDeInversionDTO.getEstablecido().toLowerCase() + "%"));
		}
		if (lineaDeInversionDTO.getDescripcion() != null) {
			predicates.add(cb.like(cb.lower(lineaDeInversion.get("descripcion")),
					"%" + lineaDeInversionDTO.getDescripcion().toLowerCase() + "%"));
		}
		if (lineaDeInversionDTO.getEstado() != null) {
			predicates.add(cb.like(lineaDeInversion.get("estado").as(String.class),
					"%" + lineaDeInversionDTO.getEstado() + "%"));
		}

		if (lineaDeInversionDTO.getFecha() != null && !lineaDeInversionDTO.getFecha().equals("")) {
			Predicate p = QueryUtils.filtroFechas("fecha", lineaDeInversionDTO.getFecha(),
					NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, lineaDeInversion);
			predicates.add(p);

		}
		if (lineaDeInversionDTO.getNombreSector() != null) {
			predicates.add(cb.like(cb.lower(lineaDeInversion.get("idLsSector").get(NHSPDDConstantes.RESULTADO)),
					"%" + lineaDeInversionDTO.getNombreSector().toLowerCase() + "%"));
		}
		

		if(lineaDeInversionDTO.getColumnaOrdenar() != null && !"".equals(lineaDeInversionDTO.getColumnaOrdenar()))
        {
            if (lineaDeInversionDTO.getColumnaOrdenar().equals("estado")
                   ||  lineaDeInversionDTO.getColumnaOrdenar().equals("idLineaInversion")
                   ||  lineaDeInversionDTO.getColumnaOrdenar().equals("descripcion")
                   ||  lineaDeInversionDTO.getColumnaOrdenar().equals("concepto")
                   ||  lineaDeInversionDTO.getColumnaOrdenar().equals("establecido")
                    || lineaDeInversionDTO.getColumnaOrdenar().equals("fecha")) {
                if (lineaDeInversionDTO.getTipoOrden().equals("asc")) {
                    cq.orderBy(cb.asc(lineaDeInversion.get(lineaDeInversionDTO.getColumnaOrdenar())));
                } else {
                    cq.orderBy(cb.desc(lineaDeInversion.get(lineaDeInversionDTO.getColumnaOrdenar())));
                }

            } else {
                if (lineaDeInversionDTO.getTipoOrden().equals("asc")) {
                    cq.orderBy(cb.asc(
                            lineaDeInversion.get(lineaDeInversionDTO.getColumnaOrdenar()).get(NHSPDDConstantes.RESULTADO)));
                } else {
                    cq.orderBy(cb.desc(
                            lineaDeInversion.get(lineaDeInversionDTO.getColumnaOrdenar()).get(NHSPDDConstantes.RESULTADO)));
                }
            }
        }
        else {
            cq.orderBy(cb.asc(lineaDeInversion.get("idLineaInversion")));
        }

		
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(LineaDeInversion.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public LineaDeInversion buscarConceptoYSector(String concepto, Long sector) throws SpddExceptions {
		return inversionRepository.obtenerConceptoYIdLsSector(concepto, sector);
	}

	@Override
	public List<LineaDeInversion> buscarPorSector(String sector) throws SpddExceptions {
		return inversionRepository.obtenerLineaPorSector(sector);
	}

}
