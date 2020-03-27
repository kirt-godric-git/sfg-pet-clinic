package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
	private final PetRepository petRepository;

	public PetSDJpaService(PetRepository petTypeRepository) {
		//super();
		this.petRepository = petTypeRepository;
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> petTypes = new HashSet<>();
		// Using Method Reference instead of Lambda syntax/expression where a method 
		// already exists to perform an operation on the class...
		// findAll() returns an Iterator interface and calls on forEach() method while passing
		// Set's owners and calling it's method add() --> petTypes.add(e)
		petRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	@Override
	public Pet findById(Long id) {
		/*Optional<Pet> optionalPet = petRepository.findById(id);
		if(optionalPet.isPresent()) 
			return optionalPet.get();
		else 
			return null;*/
		// Same as the code above
		return petRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet object) {
		return petRepository.save(object);
	}

	@Override
	public void delete(Pet object) {
		petRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);
	}
}
