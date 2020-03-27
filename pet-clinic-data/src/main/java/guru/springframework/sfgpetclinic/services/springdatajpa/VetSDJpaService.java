package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {
	private final VetRepository vetRepository;

	public VetSDJpaService(VetRepository vetRepository) {
		//super();
		this.vetRepository = vetRepository;
	}

	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<>();
		// Using Method Reference instead of Lambda syntax/expression where a method 
		// already exists to perform an operation on the class...
		// findAll() returns an Iterator interface and calls on forEach() method while passing
		// Set's owners and calling it's method add() --> vets.add(e)
		vetRepository.findAll().forEach(vets::add);
		return vets;
	}

	@Override
	public Vet findById(Long id) {
		/*Optional<Vet> optionalVet = vetRepository.findById(id);
		if(optionalVet.isPresent()) 
			return optionalVet.get();
		else 
			return null;*/
		// Same as the code above
		return vetRepository.findById(id).orElse(null);
	}

	@Override
	public Vet save(Vet object) {
		System.out.println("###############################");
		System.out.println("###############################");
		System.out.println("Saving Vet object here...");
		return vetRepository.save(object);
	}

	@Override
	public void delete(Vet object) {
		vetRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);
	}

}
