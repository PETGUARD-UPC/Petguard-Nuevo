package pe.edu.upc.controller;

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

import com.sun.el.parser.ParseException;

import pe.edu.upc.entity.Cuidador;
import pe.edu.upc.serviceinterface.ICuidadorService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/keepers")
public class CuidadorController {

	@Autowired
	private IUsuarioService uS;
	@Autowired
	private ICuidadorService cS;
	
	@GetMapping("/new")
	public String newCuidado(Model model) {

		model.addAttribute("keeper", new Cuidador());
		return "keeper/keeper";
	}
	
	@PostMapping("/save")
	public String saveCuidador(@Valid Cuidador cui,
			BindingResult result,Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			return"keeper/keeper";
		}else {
			cS.insert(cui);
		}
		model.addAttribute("listaCuidadores",cS.list());
		return "redirect:/keepers/list";
	}
	
	@GetMapping("/list")
	public String listCuidadores (Model model) {
		try {
			model.addAttribute("keeper", new Cuidador());
			model.addAttribute("listaCuidadores",cS.list());
		}catch(Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "keeper/listKeeper";
	}
	
	@RequestMapping("/find")
	public String findCuidador(Model model, @Validated Cuidador cuidador)
	throws ParseException{
		List<Cuidador> listaCuidadores;
		listaCuidadores=cS.findBynameCuidador(cuidador.getNameCuidador());
		if(listaCuidadores.isEmpty()) {
			model.addAttribute("mensaje","No se encontró");
		}
		model.addAttribute("listaCuidadores", listaCuidadores);
		return "keeper/listKeeper";
		
	}
}
