package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService 
{
	private final SpecialtyRepository specialtyRepository;
	
	public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
		//super();
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public Set<Specialty> findAll() {
		Set<Specialty> specialties = new HashSet<>();
		// Using Method Reference instead of Lambda syntax/expression where a method 
		// already exists to perform an operation on the class...
		// findAll() returns an Iterator interface and calls on forEach() method while passing
		// Set's owners and calling it's method add() --> specialties.add(e)
		specialtyRepository.findAll().forEach(specialties::add);
		return specialties;
	}

	@Override
	public Specialty findById(Long id) {
		/*Optional<Specialty> optionalSpecialty = specialtyRepository.findById(id);
		if(optionalSpecialty.isPresent()) 
			return optionalSpecialty.get();
		else 
			return null;*/
		// Same as the code above
		return specialtyRepository.findById(id).orElse(null);
	}

	@Override
	public Specialty save(Specialty object) {
		return specialtyRepository.save(object);
	}

	@Override
	public void delete(Specialty object) {
		specialtyRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		specialtyRepository.deleteById(id);
	}

}
