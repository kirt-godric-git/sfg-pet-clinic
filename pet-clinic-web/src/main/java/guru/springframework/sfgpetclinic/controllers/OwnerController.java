package guru.springframework.sfgpetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired	// <-- optional to include this
	public OwnerController(OwnerService ownerService, VisitService visitService) {
		super();
		this.ownerService = ownerService;
		this.visitService = visitService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model) {
		model.addAttribute("owners", ownerService.findAll());
		
		return "owners/index";
	}
	
	@RequestMapping("/find")
	public String findOwners() {
		return "notimplemented";
	}
	
	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = ownerService.findById(ownerId);
		// TODO: Copied originally but temporarily commented for implementation is not yet there
		// in VisitService.java!!!
		/*for (Pet pet : owner.getPets()) {
			pet.setVisitsInternal(visitService.findByPetId(pet.getId()));
		}*/
		mav.addObject(owner);
		return mav;
	}	
}
