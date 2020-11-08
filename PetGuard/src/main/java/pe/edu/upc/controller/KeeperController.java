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

import pe.edu.upc.entity.Keeper;
import pe.edu.upc.serviceinterface.IKeeperService;
import pe.edu.upc.serviceinterface.IUserService;

@Controller
@RequestMapping("/keepers")
public class KeeperController {

	@Autowired
	private IKeeperService kS;
	
	@Autowired
	private IUserService uS;
	
	
	@GetMapping("/new")
	public String newKeeper(Model model) {
		
		model.addAttribute("listaUsuarios", uS.list());
		model.addAttribute("keeper", new Keeper());
		return "keeper/keeper";
	}
	
	@PostMapping("/save")
	public String saveKeeper(@Valid Keeper keeper,BindingResult result,Model model,
			SessionStatus status) throws Exception{
			if(result.hasErrors()) {
		//	model.addAttribute("listaUsuarios", uS.list());
			return"keeper/keeper";
			}
			int rpta=kS.insert(keeper);
			//int rptaE=kS.searchE(keeper);
			if((rpta>0)) {
				model.addAttribute("listaUsuarios", uS.list());
				model.addAttribute("mensaje", "El DNI ya existe");
				return "/keeper/Keeper";	
			}
			else {
				model.addAttribute("listaCuidadores", kS.list());
				status.setComplete();
			}
		model.addAttribute("listaCuidadores",kS.list());
		return "redirect:/keepers/list";
	}
	
	@GetMapping("/list")
	public String listKeepers (Model model) {
		try {
			
			model.addAttribute("keeper", new Keeper());
			model.addAttribute("listaCuidadores",kS.list());
		}catch(Exception e) {
			System.out.println("Error al listar cuidadores en el controller");
		}
		return "/keeper/listKeeper";
	}
	
	@RequestMapping("/find")
	public String findBynameKeeper(Model model, @Validated Keeper keeper) throws ParseException{
		
		List<Keeper> listaCuidadores;
		listaCuidadores=kS.findBynameKeeper(keeper.getName());
		
		if(listaCuidadores.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró cuidador");
		}
		model.addAttribute("listaCuidadores", listaCuidadores);
		return "/keeper/listKeeper";
	}
	
}
