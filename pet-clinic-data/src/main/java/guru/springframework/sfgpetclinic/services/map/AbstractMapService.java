package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> 
{
	//protected Map<ID, T> map = new HashMap<>();
	protected Map<Long, T> map = new HashMap<>();
	
	Set<T> findAll() {
		return new HashSet<>(map.values());
	}
	
	T findById(ID id) {
		return map.get(id);
	}
	
	/*T save(ID id, T object) {
		map.put(id,  object);
		return object;
	}*/
	
	T save(T object) {
		if (object != null) {
			if (object.getId() == null) {
				object.setId(getNextId());
			}
			map.put(object.getId(), object);
		} else {
			throw new RuntimeException("Object cannot be null");
		}
		return object;
	}
	
	void deleteById(ID id) {
		map.remove(id);
	}
	
	void delete(T object) {
		// **** Java 8 process shortcut ****
		/**
		 * Map.entrySet() is returning is Set object & since Set is a Collection, it has access to removeIf() method.
		 * Additionally, "removeIf(Predicate<? super E> filter)" has a Predicate parameter which can use for Lambda expression.
		 * In mathematics, a predicate is commonly understood to be a boolean-valued function 'P: X? {true, false}', 
		 * called the predicate on X. Informally, a strong. It can be thought of as an operator or function that returns 
		 * a value that is either true or false.
		 * In Java 8, Predicate is a functional interface and can therefore be used as the assignment target for a lambda 
		 * expression or method reference -- it mean we can pass lambda expressions. 
		 * In "removeIf(Predicate<? super E> filter)" the default implementation traverses all elements of the collection 
		 * using its iterator(). Predicate has one abstract method named "test(T t)" which we will implement.
		 * Therefore we can use any individual map object variable like "entry" here and we 
		 * we add the boolean function "entry.getValue().equals(object)" to say whether to remove or not. 
		 * Translation is: map.entrySet().removeIf((Map<ID, T> thisMap) -> thisMap.getValue().equals(object));
		 */
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
		
		// **** Java 7 process ****
		/*// Get the iterator over the HashMap 
		Iterator<Entry<ID, T>> iterator = map.entrySet().iterator(); 
	    // Iterate over the HashMap 
	    while (iterator.hasNext()) { 
	        // Get the entry at this iteration 
	        Map.Entry<ID, T> entry = iterator.next(); 
	
	        // Check if this value is the required value 
	        if (entry.getValue().equals(object)) { 
	            // Remove this entry from HashMap 
	            iterator.remove(); 
	        } 
	    } */
	}
	
	private Long getNextId() {
		Long nextId = null;
		try {
			nextId = Collections.max(map.keySet()) + 1;
		} catch (NoSuchElementException e) {
			nextId = 1L;
		}
		return nextId;
	}
}
