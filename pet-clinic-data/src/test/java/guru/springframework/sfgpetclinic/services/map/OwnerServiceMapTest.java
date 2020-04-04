package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import guru.springframework.sfgpetclinic.model.Owner;

class OwnerServiceMapTest {

	OwnerServiceMap ownerServiceMap; 
	final Long ownerId = 1L;
	final String lastName = "Smith";
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("\nCalling setUp()....");
		ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
		ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	//void testSaveOwner() {
	void saveExistingId() {
		System.out.println("Test saveExistingId()....");
		Long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerServiceMap.save(owner2);
		assertEquals(id, savedOwner.getId());
		System.out.println("ownerServiceMap.findAll().size() = "+ownerServiceMap.findAll().size());
	}
	
	@Test
	void saveNoId() {
		System.out.println("Test saveNoId()....");
		Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
		System.out.println("savedOwner.getId() = "+savedOwner.getId());
		System.out.println("ownerServiceMap.findAll().size() = "+ownerServiceMap.findAll().size());
	}	

	@Test
	void testFindAll() {
		System.out.println("Test testFindAll()....");
		Set<Owner> ownerSet = ownerServiceMap.findAll();
		
		assertEquals(1, ownerSet.size());
		System.out.println("ownerServiceMap.findAll().size() = "+ownerServiceMap.findAll().size());
	}

	@Test
	void testFindByIdLong() {
		System.out.println("Test testFindByIdLong()....");
		Owner owner = ownerServiceMap.findById(ownerId);
		
		assertEquals(ownerId, owner.getId());
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteByIdLong() {
		System.out.println("Test testDeleteByIdLong()....");
		assertEquals(1, ownerServiceMap.findAll().size());
		ownerServiceMap.deleteById(ownerId);
		assertEquals(0, ownerServiceMap.findAll().size());
	}

	@Test
	void testDeleteOwner() {
		System.out.println("Test testDeleteOwner()....");
		assertEquals(1, ownerServiceMap.findAll().size());
		ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
		assertEquals(0, ownerServiceMap.findAll().size());
	}

	@Test
	void testFindByLastName() {
		System.out.println("Test testFindByLastName()....");
		Owner smith = ownerServiceMap.findByLastName(lastName);
		assertNotNull(smith);
		assertEquals(lastName, smith.getLastName());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		System.out.println("Test testFindByLastNameNotFound()....");
		Owner foo = ownerServiceMap.findByLastName("foo");
		assertNull(foo);
	}

}
