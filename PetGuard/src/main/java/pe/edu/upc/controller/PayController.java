package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Pay;
import pe.edu.upc.serviceinterface.IBanking;
import pe.edu.upc.serviceinterface.IPayService;

@Controller
@RequestMapping("/pays")
public class PayController {
	
	@Autowired
	public IPayService pS;
	
	@Autowired
	public IBanking bS;
	
	
	@GetMapping("/new")
	public String newPay(Model model){
		
		model.addAttribute("listaBancos", bS.list());
		model.addAttribute("pay", new Pay());
		return "pay/pay";
	}
	
	@PostMapping("/save")
	public String savePay(@Valid Pay pay, BindingResult result,  Model model,
			SessionStatus status) throws Exception{
		
		if(result.hasErrors()) {
			model.addAttribute("listaBancos", bS.list());
			return "pay/pay";
		}else {
			int rpta = pS.insert(pay);
			if (rpta > 0) {
				model.addAttribute("mensaje", "El metodo de pago ya existe!!");
				return "pay/pay";
			} else {
				model.addAttribute("listaMedioPagos", pS.list());
				return "redirect:/pays/list";
			}
		}
	}
	
	@GetMapping("/list")
	public String listPay(Model model) {
		
		try {
			model.addAttribute("pay", new Pay());
			model.addAttribute("listaMedioPagos", pS.list());
		} catch (Exception e) {
			System.out.println("Error al insertar mediopago en controller");
		}
		return "/pay/listPay";
	}
	
	@RequestMapping("/find")
	public String findBynamePay(Model model, @Validated Pay pay) throws ParseException{
		
		List<Pay> listaMedioPagos;
		listaMedioPagos=pS.findBynamePay(pay.getName());
		
		if (listaMedioPagos.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró el metodo de pago");

		}
		model.addAttribute("listaMedioPagos", listaMedioPagos);
		return "/pay/listPay";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteLaboratory(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				pS.delete(id);
			}
			model.addAttribute("pay", new Pay());
			model.addAttribute("mensaje", "Se eliminó correctamente!");
			model.addAttribute("listaMedioPagos", pS.list());
		} catch (Exception e) {
			model.addAttribute("pay", new Pay());
			model.addAttribute("mensaje", "No se puede eliminar!!");
			model.addAttribute("listaMedioPagos", pS.list());

		}
		return "/pay/listPay";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Pay> objPay = pS.searchId(id);
		if (objPay == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			model.addAttribute("listaBancos", bS.list());
			return "redirect:/pays/list";
		} else {
			model.addAttribute("listaMedioPagos", pS.list());
			model.addAttribute("listaBancos", bS.list());
			model.addAttribute("pay", objPay.get());
			return "pay/upay";

		}

	}

	@PostMapping("/update")
	public String updateVaccine(@Valid Pay pay, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "pay/pay";
		} else {
			pS.insert(pay);
			model.addAttribute("mensaje", "Registro actualizado correctamente");
		}
		model.addAttribute("listaMedioPagos", pS.list());
		return "redirect:/pays/list";
	}
}
