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

import pe.edu.upc.entity.Rol;
import pe.edu.upc.serviceinterface.IRolService;

@Controller
@RequestMapping("/rols")
public class RolController {

	@Autowired
	private IRolService rS;

	@GetMapping("/new")
	public String newRol(Model model) {
		model.addAttribute("rol", new Rol());
		return "rol/rol";
	}

	@PostMapping("/save")
	public String saveRol(@Valid Rol rol, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "rol/rol";
		} else {
			rS.insertar(rol);
		}
		model.addAttribute("listaRoles", rS.list());
		return "/rol/listRols";
	}

	@GetMapping("/list")
	public String listRols(Model model) {
		try {
			model.addAttribute("listRols", rS.list());
		} catch (Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "/rol/listRols";
	}

}
