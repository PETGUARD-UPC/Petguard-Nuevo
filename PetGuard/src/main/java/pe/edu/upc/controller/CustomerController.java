package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Customer;
import pe.edu.upc.serviceinterface.ICustomerService;
import pe.edu.upc.serviceinterface.IUserService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private ICustomerService cS;
	
	@Autowired
	private IUserService uS;
	
	@GetMapping("/new")
	public String newCustomer(Model model) {
		
		model.addAttribute("listaUsuarios", uS.list());
		model.addAttribute("customer", new Customer());
		return "customer/customer";
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@Valid Customer customer, BindingResult result, Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaUsuarios", uS.list());
			return"customer/customer";
		}else {
			cS.insert(customer);
		}
		model.addAttribute("listaClientes", cS.list());
		return "redirect:/customers/list";
	}
	
	
	@GetMapping("/list")
	public String listCustomers (Model model) {
		try {
			
			model.addAttribute("customer", new Customer());
			model.addAttribute("listaClientes",cS.list());
		}catch(Exception e) {
			System.out.println("Error al listar clientes en el controller");
		}
		return "/customer/listCustomer";
	}
	
	@RequestMapping("/find")
	public String findBynameCustomer(Model model, @Validated Customer customer) throws ParseException{
		
		List<Customer> listaClientes;
		listaClientes=cS.findBynameCustomer(customer.getName());
		
		if (listaClientes.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró Cliente");	
		}
		model.addAttribute("listaClientes", listaClientes);
		return "/customer/listCustomer";
	}

}
