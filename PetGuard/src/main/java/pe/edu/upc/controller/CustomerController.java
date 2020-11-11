package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Customer;
import pe.edu.upc.serviceinterface.ICustomerService;
import pe.edu.upc.serviceinterface.IUploadFileService;
import pe.edu.upc.serviceinterface.IUserService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerService cS;

	@Autowired
	private IUserService uS;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newCustomer(Model model) {

		model.addAttribute("listaUsuarios", uS.list());
		model.addAttribute("customer", new Customer());
		return "customer/customer";
	}

	@PostMapping("/save")
	public String saveCustomer(@Valid Customer customer, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaUsuarios", uS.list());
			return "customer/customer";
		} else {
			if (!foto.isEmpty()) {

				if (customer.getIdCustomer() > 0 && customer.getFoto() != null && customer.getFoto().length() > 0) {

					uploadFileService.delete(customer.getFoto());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				customer.setFoto(uniqueFilename);
			}
		}

		int rpta = cS.insert(customer);
		if (rpta > 0) {
			model.addAttribute("listaUsuarios", uS.list());
			model.addAttribute("mensaje", "El DNI ya existe");
			return "/customer/customer";
		} else {
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("listaUsuarios", uS.list());
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaClientes", cS.list());
		model.addAttribute("listaUsuarios", uS.list());
		return "redirect:/customers/list";
	}

	@GetMapping("/list")
	public String listCustomers(Model model) {
		try {
			model.addAttribute("customer", new Customer());
			model.addAttribute("listaClientes", cS.list());
		} catch (Exception e) {
			System.out.println("Error al listar clientes en el controller");
		}
		return "/customer/listCustomer";
	}

	@RequestMapping("/find")
	public String findBynameCustomer(Model model, @Validated Customer customer) throws ParseException {

		List<Customer> listaClientes;
		listaClientes = cS.findBynameCustomer(customer.getName());

		if (listaClientes.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró Cliente");
		}
		model.addAttribute("listaClientes", listaClientes);
		return "/customer/listCustomer";
	}

	@RequestMapping("/delete/{id}")
	public String deleteCustomer(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				cS.delete(id);
				model.addAttribute("listaClientes", cS.list());
				model.addAttribute("customer", new Customer());
				model.addAttribute("mensaje", "Se elimino correctamente");
			}
			return "redirect:/customers/list";
		} catch (Exception e) {
			model.addAttribute("customer", new Customer());
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar un cliente relacionado");
			model.addAttribute("listaClientes", cS.list());

			return "customer/listCustomer";
		}
	}

	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Customer> objAr = cS.searchId(id);
		if (objAr == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/customers/list";
		} else {
			model.addAttribute("listaUsuarios", uS.list());
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("customer", objAr.get());
			return "customer/ucustomer";
		}
	}

	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Customer> customer = cS.searchId(id);
		if (customer == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/customers/list";
		}

		model.addAttribute("customer", customer.get());

		return "customer/view";
	}

	@PostMapping("/update")
	public String updateCustomer(@Valid Customer customer, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaUsuarios", uS.list());
			return "customer/customer";
		} else {
			if (!foto.isEmpty()) {

				if (customer.getIdCustomer() > 0 && customer.getFoto() != null && customer.getFoto().length() > 0) {

					uploadFileService.delete(customer.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				customer.setFoto(uniqueFilename);
			}
			cS.insert(customer);
		}
		model.addAttribute("listaUsuarios", uS.list());
		model.addAttribute("listaClientes", cS.list());
		model.addAttribute("customer", new Customer());
		return "redirect:/customers/list";
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

}
