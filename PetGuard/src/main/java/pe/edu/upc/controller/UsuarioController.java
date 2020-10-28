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

import pe.edu.upc.entity.Usuario;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService uS;
	
	@GetMapping("/new")
	public String newUser(Model model) {
		
		model.addAttribute("user", new Usuario());
		return "user/user";
	}
	
	@PostMapping("/save")
	public String saveUser(@Valid Usuario usu, BindingResult result, Model model,
			SessionStatus status) throws Exception {	
		if(result.hasErrors()) {
			return "user/user";
		}else {
			uS.insert(usu);
		}
		model.addAttribute("listaUsuario", uS.list());
		return "/user/listUser";
		
	}
	
	@GetMapping("/list")
	public String listUsers(Model model) {
		try {
			model.addAttribute("listaUsuario", uS.list());
		} catch (Exception e) {
			System.out.println("no se pudo listar los usuarios en el controller");
		}
		return "/user/listUser";
		
	}
}
