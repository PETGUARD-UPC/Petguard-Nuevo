package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Pet;
import pe.edu.upc.serviceinterface.ICustomerService;
import pe.edu.upc.serviceinterface.IPetService;

@Controller
@RequestMapping("/pets")
public class PetController {
	
	@Autowired
	private IPetService mS;
	
	@Autowired
	private ICustomerService cS;
	
	
	@GetMapping("/new")
	public String newPet(Model model) {
		
		model.addAttribute("listaClientes", cS.list());
		
		model.addAttribute("pet", new Pet());
		return "pet/pet";
	}
	
	@PostMapping("/save")
	public String savePet(@Valid Pet pet,BindingResult result,Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaClientes", cS.list());
			return"pet/pet";
		}else {
			mS.insert(pet);
		}
		model.addAttribute("listaMascotas",mS.list());
		return "redirect:/pets/list";
	}
	
	@GetMapping("/list")
	public String listPets(Model model) {
		try {
			model.addAttribute("listaMascotas",mS.list());
		}catch(Exception e) {
			System.out.println("Error al listar mascotas en el controller");
		}
		return "/pet/listPet";
	}
}
