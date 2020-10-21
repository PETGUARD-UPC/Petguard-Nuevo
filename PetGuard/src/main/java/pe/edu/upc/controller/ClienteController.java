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

import pe.edu.upc.entity.Cliente;
import pe.edu.upc.serviceinterface.IClienteService;

@Controller
@RequestMapping("/customers")
public class ClienteController {
	@Autowired
	private IClienteService cS;
	
	@GetMapping("/new")
	public String newCliente(Model model) {
		model.addAttribute("customers", new Cliente());
		return "customers/customers";
	}
	
	@PostMapping("/save")
	public String saveCliente(@Valid Cliente cli, BindingResult result, Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			return"customers/customers";
		}else {
			cS.insert(cli);
		}
		model.addAttribute("listaCliente",cS.list());
		return "/customers/listCustomers";
	}
	
	@GetMapping("/list")
	public String listCliente (Model model) {
		try {
			model.addAttribute("listaCliente",cS.list());
		}catch(Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "/customers/listCustomers";
	}

}
