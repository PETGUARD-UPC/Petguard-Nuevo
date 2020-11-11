package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Reserve;
import pe.edu.upc.serviceinterface.ICustomerService;
import pe.edu.upc.serviceinterface.IKeeperService;
import pe.edu.upc.serviceinterface.IPayService;
import pe.edu.upc.serviceinterface.IReserveService;

@Controller
@RequestMapping("/reserves")
public class ReserveController {

	@Autowired
	private IReserveService rS;
	
	@Autowired
	private ICustomerService cS;
	
	@Autowired
	private IKeeperService kS;
	
	@Autowired
	private IPayService pS;
	
	
	@GetMapping("/new")
	public String newReserve(Model model) {
		
		model.addAttribute("listaClientes", cS.list());
		model.addAttribute("listaCuidadores", kS.list());
		model.addAttribute("listaMedioPagos", pS.list());
		
		model.addAttribute("reserve", new Reserve());
		return "reserve/reserve";
	}
	
	@PostMapping("/save")
	public String saveReserve(@Valid Reserve res, BindingResult result, Model model,
			SessionStatus status) throws Exception {	
		if(result.hasErrors()) {
			
			//cuando guardes y te salga las validaciones te tiene que volver a cargar las listas
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("listaCuidadores", kS.list());
			model.addAttribute("listaMedioPagos", pS.list());
			return "reserve/reserve";
		}else {
			rS.insert(res);
		}
		model.addAttribute("listaReservas", rS.list());
		return "redirect:/reserves/list";
	}
	
	@GetMapping("/list")
	public String listReserves(Model model) {
		try {
			model.addAttribute("reserve", new Reserve());
			model.addAttribute("listaReservas", rS.list());
		} catch (Exception e) {
			System.out.println("no se pudo listar las reservas en el controller");
		}
		return "/reserve/listReserve";
		
	}
	
	@RequestMapping("/find")
	public String findBycustomer(Map<String, Object> model, @ModelAttribute Reserve reserve) throws ParseException{
		
		List<Reserve> listaReservas;
		reserve.setSite(reserve.getSite());
		listaReservas=rS.findBycustomer(reserve.getSite());
		
		if(listaReservas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listaReservas", listaReservas);
		return  "reserve/listReserve";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteReserve(Model model, @PathVariable(value = "id") int id) {
		
		try {
			if(id>0) {
				rS.delete(id);
			}
			model.addAttribute("reserve", new Reserve());
			model.addAttribute("mensaje", "Se eliminó correctamente");
			model.addAttribute("listaReservas", rS.list());
			
			
		} catch (Exception e) {
			
			model.addAttribute("reserve", new Reserve());
			
			model.addAttribute("mensaje", "No se puede eliminar!");
			model.addAttribute("listaReservas", rS.list());
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("listaCuidadores", kS.list());
			model.addAttribute("listaMedioPagos", pS.list());
		}
		return  "reserve/listReserve";
	}
	
	@GetMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		
		Optional<Reserve> objRes= rS.searchId(id);
		
		if(objRes == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/reserves/list";
		}else {
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("listaCuidadores", kS.list());
			model.addAttribute("listaMedioPagos", pS.list());
			
			model.addAttribute("reserve", objRes.get());
			return "reserve/ureserve";
		}
		
	}
	
	
	@PostMapping("/update")
	public String updateReserve(@Valid Reserve reserve, BindingResult result, Model model,
			SessionStatus statuts) throws Exception{
		
		if(result.hasErrors()) {
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("listaCuidadores", kS.list());
			model.addAttribute("listaMedioPagos", pS.list());
			return "reserve/reserve";
		}else {
			rS.insert(reserve);
			model.addAttribute("mensaje", "Registro actualizado correctamente");
		}
		
		model.addAttribute("listaReservas", rS.list());
		return  "redirect:/reserves/list";
	}
		
}
