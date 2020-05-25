package guru.springframework.sfgpetclinic.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Controller
public class VisitController {

	private final VisitService visitService;

	private final PetService petService;

	public VisitController(VisitService visitService, PetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
	 * we always have fresh data - Since we do not use the session scope, make sure that
	 * Pet object always has an id (Even though id is not part of the form fields)
	 * @param petId
	 * @return Pet
	 */
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {
		Pet pet = petService.findById(petId);
		//pet.setVisitsInternal(visitService.findByPetId(petId));	// <-- commented we don't have it.
		//pet.setVisits(visitService.findByPetId(petId));			// <-- possible replacement but not yet implemented. 
		model.put("pet", pet);
		Visit visit = new Visit();
		pet.addVisit(visit);
		return visit;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
		return "pets/createOrUpdateVisitForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}
		else {
			visitService.save(visit);
			return "redirect:/owners/{ownerId}";
		}
	}
}
