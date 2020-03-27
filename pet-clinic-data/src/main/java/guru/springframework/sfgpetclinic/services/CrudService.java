package guru.springframework.sfgpetclinic.services;

import java.util.Optional;
import java.util.Set;

public interface CrudService<T, ID> {
	/**
	 * Returns all instances of the type.
	 * Mimics CrudRepository's "Iterable<T> findAll();"
	 * @return
	 */
	Set<T> findAll();
	
	/**
	 * Retrieves an entity by its id.
	 * Mimics CrudRepository's "Optional<T> findById(ID id);"
	 * @param id
	 * @return
	 */
	T findById(ID id);
	
	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might 
	 * have changed the entity instance completely.
	 * Mimics CrudRepository's "<S extends T> S save(S entity);"
	 * @param object
	 * @return
	 */
	T save(T object);
	
	/**
	 * Deletes a given entity.
	 * Mimics CrudRepository's "void delete(T entity);"
	 * @param object
	 */
	void delete(T object);
	
	/**
	 * Deletes the entity with the given id.  
	 * Mimics CrudRepository's "void deleteById(ID id);"
	 * @param id
	 */
	void deleteById(ID id);
}
