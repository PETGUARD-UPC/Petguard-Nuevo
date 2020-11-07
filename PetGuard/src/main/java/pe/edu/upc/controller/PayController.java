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

import pe.edu.upc.entity.Pay;
import pe.edu.upc.serviceinterface.IPayService;

@Controller
@RequestMapping("/pays")
public class PayController {
	
	@Autowired
	public IPayService pS;
	
	@GetMapping("/new")
	public String newPay(Model model){
		model.addAttribute("pay", new Pay());
		return "pay/pay";
	}
	
	@PostMapping("/save")
	public String savePay(@Valid Pay pay, BindingResult result,  Model model,
			SessionStatus status) throws Exception{
		
		if(result.hasErrors()) {
			return "pay/pay";
		}else {
			pS.insert(pay);
		}
		model.addAttribute("listaMedioPagos", pS.list());
		
		return "redirect:/pays/list";
	}
	
	@GetMapping("/list")
	public String listPay(Model model) {
		
		try {
			model.addAttribute("listaMedioPagos", pS.list());
		} catch (Exception e) {
			System.out.println("Error al insertar mediopago en controller");
		}
		return "/pay/listPay";
	}
}
