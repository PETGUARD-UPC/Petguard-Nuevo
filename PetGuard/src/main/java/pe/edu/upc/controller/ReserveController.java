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
import pe.edu.upc.entity.ReserveDetail;
import pe.edu.upc.serviceinterface.ICustomerService;
import pe.edu.upc.serviceinterface.IKeeperService;
import pe.edu.upc.serviceinterface.IPayService;
import pe.edu.upc.serviceinterface.IReserveDetailService;
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
	
	@Autowired
	private IReserveDetailService idrS;
	
	@RequestMapping("/reports")
	public String Report()
	{
		return "reports/reports";
	}
	
	@GetMapping("/new")
	public String newReserve(Model model) {
		
		model.addAttribute("listaClientes", cS.list());
		model.addAttribute("listaCuidadores", kS.list());
		model.addAttribute("listaMedioPagos", pS.list());
		
		model.addAttribute("reserve", new Reserve());
		return "reserve/reserve";
	}
	@RequestMapping("/newreserve/{id}")
	public String irNewReserve(@PathVariable(value = "id") Integer id, Map<String, Object> model) {

		model.put("detail", new ReserveDetail());
		model.put("listaCuidadores", kS.list());

		Reserve objres = rS.listarId(id);
		model.put("reserve", objres);

		return "reserve/details/detailForm";
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
	@PostMapping("/savekeeper{id}")
	public String newHourXReserve(@PathVariable(value = "id") int id, @Valid ReserveDetail reservedet,
			RedirectAttributes flash, BindingResult result, Model model, SessionStatus status) {
		Reserve res = rS.listarId(id);
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", "El valor debe ser positivo");
			String cadena1 = "redirect:/reserve/newreserve/" + id;
			return cadena1;
		}
		try {
			res.addDetailReserve(reservedet);
			rS.insert(res);
			status.isComplete();
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
		}
		String cadena = "redirect:/reserves/detail/" + id;
		return cadena;
	}
	/*
	 * @GetMapping("/list") public String listReserves(Model model) { try {
	 * model.addAttribute("reserve", new Reserve());
	 * model.addAttribute("listaReservas", rS.list()); } catch (Exception e) {
	 * System.out.println("no se pudo listar las reservas en el controller"); }
	 * return "/reserve/listReserve";
	 * 
	 * }
	 */
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaReservas", rS.listar());
		return "reserve/listReserve";
	}
	@GetMapping("/detail/{id}")
	public String detailReserve(@PathVariable(value = "id") Integer id, Map<String, Object> model,
			RedirectAttributes flash) {
		Reserve res = rS.listarId(id);

		if (res == null) {
			flash.addFlashAttribute("error", "El Detalle no existe en la base de datos");
			return "reserve/listReserve";
		}
		model.put("reserve", res);
		model.put("titulo", "Detalle de Reserva #" + res.getIdReserve());
		return "reserve/details/listDetail";
	}
	@RequestMapping("{idres}/eliminardetail/{id}")
	public String eliminarDetalle(Map<String, Object> model, @PathVariable(value = "id") Integer idret,
			@PathVariable(value = "idres") Integer idres, RedirectAttributes flash) {
		try {
			if (idret != null && idret > 0) {
				idrS.delete(idret);
				flash.addAttribute("mensaje", "Se eliminó correctamente");
				flash.addAttribute("mensaje1", "Se eliminó correctamente el id" + idret);
			} else
				return "redirect:/home";
		} catch (Exception e) {
			model.put("mensaje", "No se puede eliminar");
			model.put("error", e.getMessage());
		}
		String cadena = "redirect:/reserves/detail/" + idres;
		return cadena;
	}
	/*
	 * @RequestMapping("/find") public String findBycustomer(Map<String, Object>
	 * model, @ModelAttribute Reserve reserve) throws ParseException{
	 * 
	 * List<Reserve> listaReservas; reserve.setSite(reserve.getSite());
	 * listaReservas=rS.findBycustomer(reserve.getSite());
	 * 
	 * if(listaReservas.isEmpty()) { model.put("mensaje", "No se encontró"); }
	 * 
	 * model.put("listaReservas", listaReservas); return "reserve/listReserve"; }
	 */
	
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
		
	@RequestMapping("/reporte1")
	public String categoryTop(Map<String, Object> model) {
		model.put("listaReservasTop", rS.reserveTop());
		return "reports/reserveTop";
	}
}
