package guru.springframework.sfgpetclinic.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VisitService;

@RequestMapping("/owners")	// <-- prefix every path for below later
@Controller
public class OwnerController {
	private final OwnerService ownerService;
	private final VisitService visitService;
	
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	
	@Autowired	// <-- optional to include this
	public OwnerController(OwnerService ownerService, VisitService visitService) {
		super();
		this.ownerService = ownerService;
		this.visitService = visitService;
	}

	/* KNOTES:
	 * @InitBinder --> Annotation that identifies methods which initialize the WebDataBinder which
	 * will be used for populating command and form object arguments of annotated handler methods.
	 * Init-binder methods must not have a return value; they are usually declared as void.
	 * WebDataBinder --> Special DataBinder for data binding from web request parameters to JavaBean 
	 * objects. Designed for web environments, but not dependent on the Servlet API; serves as base 
	 * class for more specific DataBinder variants, such as org.springframework.web.bind.ServletRequestDataBinder.
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		System.out.println("OwnerController.setAllowedFields() method called...");
		dataBinder.setDisallowedFields("id");
	}
	
	/* No longer needed. Replaced by processFindForm() as default path on "/owners"
	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model) {
		model.addAttribute("owners", ownerService.findAll());
		
		return "owners/index";
	}*/
	
	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}
	
	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		System.out.println("OwnerController.processFindForm() method called...");
		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
		
		if (results.isEmpty()) {
			System.out.println("No owners found! Will go to 'owners/findOwners' path...");
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (results.size() == 1) {
			System.out.println("One owner found! Will redirect to 'owners/{ownerId}' path...");
			// 1 owner found
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		}
		else {
			System.out.println("Multiple owners found! Will go to 'owners/ownersList' path...");
			// multiple owners found
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}
	
	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		System.out.println("OwnerController.showOwner() method called...");
		
		/*
		 * ModelAndView --> Convenient constructor when there is no model data to expose.
		 * Can also be used in conjunction with {@code addObject}.
		 */
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = ownerService.findById(ownerId);
		// TODO: Copied originally but temporarily commented for implementation is not yet there
		// in VisitService.java!!!
		/*for (Pet pet : owner.getPets()) {
			pet.setVisitsInternal(visitService.findByPetId(pet.getId()));
		}*/
		
		// ModelAndView.addObject --> Add an attribute to the model using parameter name generation.
		// Therefore owner here passed will be used as attribute name 'owner' in th:object="${owner}"...
		mav.addObject(owner);
		return mav;
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		//model.addAttribute("owner", new Owner()); 			// OK - Using traditional
		model.addAttribute("owner", Owner.builder().build());	// OK - Using Lombok
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
	
	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			//return "redirect:/owners/{ownerId}";
			return "redirect:/owners/"+savedOwner.getId();
		}
	}
}
