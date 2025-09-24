package co.gov.sdp.spdd.data.operacionesbasicas.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public abstract class OperacionesBasicasFacade<T, I extends Serializable> implements IOperacionesBasicasFacade<T, I> {

	/**
	 * Metodo que guarda en la bd
	 */
	@Override
	public T guardar(T entity) {
		return getRepo().save(entity);
	}

	/**
	 * Metodo que elimna de la bd
	 */
	@Override
	public void eliminar(I id) {
		getRepo().deleteById(id);
	}

	/**
	 * Metodo que busca por id
	 */
	@Override
	public T obtener(I id) {
		Optional<T> obj = getRepo().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	/**
	 * Metodo que retorna una lista tipo <T>
	 */
	@Override
	public List<T> obtenerTodos() {
		List<T> returnArrayList = new ArrayList<>();
		getRepo().findAll().forEach(returnArrayList::add);
		return returnArrayList;
	}

	/**
	 * Metodo abstrac para utilizar esta clase en diferente entidades
	 *
	 * @return returna la entidad
	 */
	public abstract CrudRepository<T, I> getRepo();

}
