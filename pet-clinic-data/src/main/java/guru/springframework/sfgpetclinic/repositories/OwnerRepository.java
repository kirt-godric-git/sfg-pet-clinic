package guru.springframework.sfgpetclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Owner findByLastName(String lastName); // <-- This will use Dynamic Queries of SpringDataJPA
	
	// *** Version from Spring Pet Clinic --> OK working also
	/*@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
	@Transactional(readOnly = true)
	List<Owner> findAllByLastNameLike(@Param("lastName") String lastName);*/
	
	// *** Version from Spring Guru speaker
	List<Owner> findAllByLastNameLike(String lastName);
}
