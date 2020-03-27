package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // <-- This will register the class as a Spring Bean and as a Controller in Spring MVC 
public class IndexController {
	
	@RequestMapping({"", "/", "index", "index.html"})	// <-- To map methods to http request paths use @RequestMapping 
	public String index() {
		return "index"; // this will go to index.html
	}
	
	@RequestMapping("/oups")
	public String oupsHandler() {
		return "notimplemented";
	}
}
