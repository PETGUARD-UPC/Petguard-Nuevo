package pe.edu.upc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Banking;
import pe.edu.upc.serviceinterface.IBanking;


@Controller
@RequestMapping("/bankings")
public class BankingController {
	
	@Autowired
	public IBanking bS;
	
	@GetMapping("/new")
	public String newBanking(Model model) {
		model.addAttribute("banking", new Banking());
		return "banking/banking";
	}
	
	@PostMapping("/save")
	public String saveBanking(@Valid Banking banking, BindingResult result, Model model,
			SessionStatus status) throws Exception{
		
		if(result.hasErrors()) {
			return "banking/banking";
		}else {
			int rpta=bS.insert(banking);
			if (rpta>0) {
				model.addAttribute("mensaje", "La entidad de banco ya existe!!");
				return  "/banking/banking";
			}else {
				model.addAttribute("listaBancos", bS.list());
				return "redirect:/bankings/list";
			}
				
		}
		
	}
	
	@GetMapping("/list")
	public String listBanking(Model model) {
		
		try {
			model.addAttribute("banking", new Banking());
			model.addAttribute("listaBancos", bS.list());
			
		} catch (Exception e) {
			System.out.println("Error al insertar entidad bancaria en controller");
		}
	
		return "/banking/listBanking";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteLaboratory(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				bS.delete(id);
			}
			model.addAttribute("banking", new Banking());
			model.addAttribute("mensaje", "Se eliminó correctamente!");
			model.addAttribute("listaBancos", bS.list());
		} catch (Exception e) {
			model.addAttribute("banking", new Banking());
			model.addAttribute("mensaje", "No se puede eliminar!!");
			model.addAttribute("listaBancos", bS.list());

		}
		return "/banking/listBanking";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Banking> objBan = bS.searchId(id);
		if (objBan == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/bankings/list";
		} else {
			model.addAttribute("banking", objBan.get());
			return "banking/ubanking";

		}

	}

	@PostMapping("/update")
	public String updateVaccine(@Valid Banking banking, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "banking/banking";
		} else {
			bS.insert(banking);
			model.addAttribute("mensaje", "Registro actualizado correctamente");
		}
		model.addAttribute("listaBancos", bS.list());
		return "redirect:/bankings/list";

	}
	

}
