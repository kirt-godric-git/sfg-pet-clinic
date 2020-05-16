package guru.springframework.sfgpetclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Owner findByLastName(String lastName); // <-- This will use Dynamic Queries of SpringDataJPA
	List<Owner> findAllByLastNameLike(String lastName);
}
