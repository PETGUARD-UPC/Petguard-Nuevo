package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
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

import pe.edu.upc.entity.Keeper;
import pe.edu.upc.serviceinterface.IKeeperService;
import pe.edu.upc.serviceinterface.IUploadFileService;
import pe.edu.upc.serviceinterface.IUserService;

@Controller
@RequestMapping("/keepers")
public class KeeperController {

	@Autowired
	private IKeeperService kS;
	
	@Autowired
	private IUserService uS;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/new")
	public String newKeeper(Model model) {
		
		model.addAttribute("listaUsuarios", uS.list());
		model.addAttribute("keeper", new Keeper());
		return "keeper/keeper";
	}
	
	@PostMapping("/save")
	public String saveKeeper(@Valid Keeper keeper,BindingResult result,Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash,
			SessionStatus status) throws Exception{
			if(result.hasErrors()) {
			model.addAttribute("listaUsuarios", uS.list());
			return"keeper/keeper";
			} else {
				if (!foto.isEmpty()) {

					if (keeper.getIdKeeper() > 0 && keeper.getFoto() != null && keeper.getFoto().length() > 0) {

						uploadFileService.delete(keeper.getFoto());
					}

					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(foto);
					} catch (IOException e) {
						e.printStackTrace();
					}

					flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
					keeper.setFoto(uniqueFilename);
				}
			}
			
			int rpta=kS.insert(keeper);
			if((rpta>0)) {
				model.addAttribute("listaUsuarios", uS.list());
				model.addAttribute("mensaje", "El DNI ya existe");
				return "/keeper/keeper";	
			}
			else {
				model.addAttribute("listaCuidadores", kS.list());
				model.addAttribute("listaUsuarios", uS.list());
				status.setComplete();
			}
		model.addAttribute("listaCuidadores",kS.list());
		model.addAttribute("listaUsuarios", uS.list());
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
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Keeper> objAr = kS.searchId(id);
		if (objAr == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/keepers/list";
		} else {
			model.addAttribute("listaUsuarios", uS.list());
			model.addAttribute("listaCuidadores", kS.list());
			model.addAttribute("keeper", objAr.get());
			return "keeper/ukeeper";
		}
	}
	
	@PostMapping("/update")
    public String updatePet(@Valid Keeper keeper,BindingResult result,Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash,
			SessionStatus status) throws Exception {
		
        if (result.hasErrors()) {
        	model.addAttribute("listaUsuarios", uS.list());
            return "keeper/keeper";
        } else {
        	if (!foto.isEmpty()) {

				if (keeper.getIdKeeper() > 0 && keeper.getFoto() != null && keeper.getFoto().length() > 0) {

					uploadFileService.delete(keeper.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				keeper.setFoto(uniqueFilename);
			}
            kS.insert(keeper);
        }
        model.addAttribute("listaUsuarios", uS.list());
        model.addAttribute("listaCuidadores", kS.list());	
        model.addAttribute("keeper)", new Keeper());
        return "redirect:/keepers/list";
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
	
	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Keeper> keeper = kS.searchId(id);
		if (keeper == null) {
			flash.addFlashAttribute("error", "El cuidador no existe en la base de datos");
			return "redirect:/pets/list";
		}

		model.addAttribute("keeper", keeper.get());

		return "keeper/view";
	}
	
	@RequestMapping("/delete/{id}")
	public String deletePet(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				kS.delete(id);
				model.addAttribute("listaCuidadores", kS.list());
				model.addAttribute("keeper", new Keeper());
				model.addAttribute("mensaje", "Se eliminó correctamente");

			}
			return "redirect:/keepers/list";

		} catch (Exception e) {
			model.addAttribute("keeper", new Keeper());

			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar el cuidador relacionada");
			model.addAttribute("listaCuidadores", kS.list());

			return "keeper/listKeeper";
		}

	}
	@RequestMapping("/reporte2")
	public String categoryTop(Map<String, Object> model) {
		model.put("listaCuidadoresTop", kS.keeperTop());
		return "reports/keeperTop";
	}
}
