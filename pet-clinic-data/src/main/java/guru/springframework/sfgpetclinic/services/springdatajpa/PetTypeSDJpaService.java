package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {
	private final PetTypeRepository petTypeRepository;

	public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
		//super();
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Set<PetType> findAll() {
		Set<PetType> petTypes = new HashSet<>();
		// Using Method Reference instead of Lambda syntax/expression where a method 
		// already exists to perform an operation on the class...
		// findAll() returns an Iterator interface and calls on forEach() method while passing
		// Set's owners and calling it's method add() --> petTypes.add(e)
		petTypeRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	@Override
	public PetType findById(Long id) {
		/*Optional<PetType> optionalPetType = petTypeRepository.findById(id);
		if(optionalPetType.isPresent()) 
			return optionalPetType.get();
		else 
			return null;*/
		// Same as the code above
		return petTypeRepository.findById(id).orElse(null);
	}

	@Override
	public PetType save(PetType object) {
		return petTypeRepository.save(object);
	}

	@Override
	public void delete(PetType object) {
		petTypeRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}
}
