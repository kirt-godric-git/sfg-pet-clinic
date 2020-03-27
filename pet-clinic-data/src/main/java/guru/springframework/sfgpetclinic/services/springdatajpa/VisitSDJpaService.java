package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {
	private final VisitRepository visitRepository;

	public VisitSDJpaService(VisitRepository vetRepository) {
		//super();
		this.visitRepository = vetRepository;
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		// Using Method Reference instead of Lambda syntax/expression where a method 
		// already exists to perform an operation on the class...
		// findAll() returns an Iterator interface and calls on forEach() method while passing
		// Set's owners and calling it's method add() --> visits.add(e)
		visitRepository.findAll().forEach(visits::add);
		return visits;
	}

	@Override
	public Visit findById(Long id) {
		/*Optional<Visit> optionalVisit = visitRepository.findById(id);
		if(optionalVisit.isPresent()) 
			return optionalVisit.get();
		else 
			return null;*/
		// Same as the code above
		return visitRepository.findById(id).orElse(null);
	}

	@Override
	public Visit save(Visit object) {
		return visitRepository.save(object);
	}

	@Override
	public void delete(Visit object) {
		visitRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

}
