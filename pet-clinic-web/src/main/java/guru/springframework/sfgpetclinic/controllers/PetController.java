package guru.springframework.sfgpetclinic.controllers;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;


@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private final PetService petService;
	private final OwnerService ownerService;
	private final PetTypeService petTypeService;
	
	public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
		super();
		this.petService = petService;
		this.ownerService = ownerService;
		this.petTypeService = petTypeService;
	}

	/* KNOTES:
	 * @ModelAttribute --> Annotation that binds a method parameter or method return value to a named model 
	 * attribute, exposed to a web view. Supported for controller classes with @RequestMapping methods. 
	 * Can be used to expose command objects to a web view, using specific attribute names, through annotating 
	 * corresponding parameters of an @RequestMapping method.
	 */
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		System.out.println("PetController.populatePetTypes() method called...");
		return petTypeService.findAll();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		System.out.println("PetController.findOwner() method called...");
		return ownerService.findById(ownerId);
	}

	/* KNOTES:
	 * @InitBinder --> Annotation that identifies methods which initialize the WebDataBinder which
	 * will be used for populating command and form object arguments of annotated handler methods.
	 * Init-binder methods must not have a return value; they are usually declared as void.
	 * WebDataBinder --> Special DataBinder for data binding from web request parameters to JavaBean 
	 * objects. Designed for web environments, but not dependent on the Servlet API; serves as base 
	 * class for more specific DataBinder variants, such as org.springframework.web.bind.ServletRequestDataBinder.
	 */
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		System.out.println("PetController.initOwnerBinder() method called...");
		dataBinder.setDisallowedFields("id");
	}
}
