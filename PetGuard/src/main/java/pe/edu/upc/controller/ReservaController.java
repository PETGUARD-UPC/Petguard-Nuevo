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

import pe.edu.upc.entity.Reserva;
import pe.edu.upc.serviceinterface.IClienteService;
import pe.edu.upc.serviceinterface.ICuidadorService;
import pe.edu.upc.serviceinterface.IMedioPagoService;
import pe.edu.upc.serviceinterface.IReservaService;

@Controller
@RequestMapping("/reservations")
public class ReservaController {

	@Autowired
	private IReservaService rS;
	
	@Autowired
	private IClienteService cS;
	
	@Autowired
	private ICuidadorService cuS;
	
	@Autowired
	private IMedioPagoService mS;
	
	
	@GetMapping("/new")
	public String newReservation(Model model) {
		
		model.addAttribute("listaClientes", cS.list());
		model.addAttribute("listaCuidadores", cuS.list());
		model.addAttribute("listaMedioPagos", mS.list());
		
		model.addAttribute("reserve", new Reserva());
		return "reservation/reservation";
	}
	
	@PostMapping("/save")
	public String saveReservation(@Valid Reserva res, BindingResult result, Model model,
			SessionStatus status) throws Exception {	
		if(result.hasErrors()) {
			return "reservation/reservation";
		}else {
			rS.insert(res);
		}
		model.addAttribute("listaReserva", rS.list());
		return "/reservation/listReservation";
	}
	
	@GetMapping("/list")
	public String listReservations(Model model) {
		try {
			model.addAttribute("listaReserva", rS.list());
		} catch (Exception e) {
			System.out.println("no se pudo listar las reservas en el controller");
		}
		return "/reservation/listReservation";
		
	}
		
}
