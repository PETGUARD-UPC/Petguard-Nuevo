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

import pe.edu.upc.entity.MedioPago;
import pe.edu.upc.serviceinterface.IMedioPagoService;

@Controller
@RequestMapping("/payments")
public class MedioPagoController {
	@Autowired
	public IMedioPagoService pS;
	
	@GetMapping("/new")
	public String newMedioPago(Model model){
		model.addAttribute("typePay", new MedioPago());
		return "pay/pay";
	}
	
	@PostMapping("/save")
	public String saveMedioPago(@Valid MedioPago mediopago, BindingResult result,  Model model,
			SessionStatus status) throws Exception{
		
		if(result.hasErrors()) {
			return "pay/pay";
		}else {
			pS.insert(mediopago);
		}
		model.addAttribute("listaMedioPago", pS.list());
		
		return "/pay/listPay";
	}
	
	@GetMapping("/list")
	public String listMedioPago(Model model) {
		
		try {
			model.addAttribute("listaMedioPago", pS.list());
		} catch (Exception e) {
			System.out.println("Error al insertar mediopago en controller");
		}
		return "/pay/listPay";
	}
}
