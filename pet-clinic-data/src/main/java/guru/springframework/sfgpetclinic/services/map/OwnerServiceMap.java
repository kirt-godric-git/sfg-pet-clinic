package guru.springframework.sfgpetclinic.services.map;

import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.CrudService;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
@Profile({"default", "map"})
//public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long>
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService
{
	private final PetTypeService petTypeService; 
	private final PetService petService; 
	
	public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Owner save(Owner object) 
	{	
		if (object != null) 
		{
			if (object.getPets() != null) 
			{
				object.getPets().forEach(pet -> {
					if (pet.getPetType() != null) {
						if (pet.getPetType().getId() == null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					} else {
						throw new RuntimeException("Pet Type is required!");
					}
					
					if (pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save(object);
			
		} else {
			return null;
		}
		
		//return super.save(object);
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return super.findAll()	// <-- will return Set<T>
				.stream()		// <-- Java 8, Returns a sequential with this collection as its source.
				// Below, returns a stream consisting of the elements of this stream that match the given predicate.	
				.filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
				.findFirst()	// <-- Returns an {@link Optional} describing the first element of this stream, 
								// or an empty {@code Optional} if the stream is empty. 
				.orElse(null);	// <-- Return the value if present, otherwise return {@code other}.
	}
	
}
