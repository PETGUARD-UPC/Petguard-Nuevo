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

import pe.edu.upc.entity.Cuidador;
import pe.edu.upc.serviceinterface.ICuidadorService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/keepers")
public class CuidadorController {

	@Autowired
	private ICuidadorService cS;
	
	@Autowired
	private IUsuarioService uS;
	
	
	@GetMapping("/new")
	public String newCuidado(Model model) {
		
		model.addAttribute("listaUsuarios", uS.list());
		
		model.addAttribute("keeper", new Cuidador());
		return "keeper/keeper";
	}
	
	@PostMapping("/save")
	public String saveCuidador(@Valid Cuidador cui,BindingResult result,Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			return"keeper/keeper";
		}else {
			cS.insert(cui);
		}
		model.addAttribute("listaCuidador",cS.list());
		return "/keeper/listKeeper";
	}
	
	@GetMapping("/list")
	public String listCuidadores (Model model) {
		try {
			model.addAttribute("listaCuidador",cS.list());
		}catch(Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "/keeper/listKeeper";
	}
	
}
