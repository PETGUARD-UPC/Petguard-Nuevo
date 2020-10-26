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

import pe.edu.upc.entity.Mascota;
import pe.edu.upc.serviceinterface.IClienteService;
import pe.edu.upc.serviceinterface.IMascotaService;

@Controller
@RequestMapping("/pets")
public class MascotaController {
	
	@Autowired
	private IMascotaService mS;
	
	@Autowired
	private IClienteService cS;
	
	
	@GetMapping("/new")
	public String newMascota(Model model) {
		
		model.addAttribute("listaCliente", cS.list());
		
		model.addAttribute("pet", new Mascota());
		return "pet/pet";
	}
	
	@PostMapping("/save")
	public String saveMascota(@Valid Mascota pet,BindingResult result,Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
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
			System.out.println("Error al listar en el controller");
		}
		return "/pet/listPet";
	}
}
