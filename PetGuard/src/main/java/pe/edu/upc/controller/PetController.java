package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
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

import pe.edu.upc.entity.Pet;
import pe.edu.upc.serviceinterface.ICustomerService;
import pe.edu.upc.serviceinterface.IPetService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/pets")
public class PetController {
	
	@Autowired
	private IPetService mS;
	
	@Autowired
	private ICustomerService cS;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/new")
	public String newPet(Model model) {
		
		model.addAttribute("listaClientes", cS.list());	
		model.addAttribute("pet", new Pet());
		return "pet/pet";
	}
	
	@PostMapping("/save")
	public String savePet(@Valid Pet pet,BindingResult result,Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaClientes", cS.list());
			return"pet/pet";
		}else {
			if (!foto.isEmpty()) {

				if (pet.getIdPet() > 0 && pet.getFoto() != null && pet.getFoto().length() > 0) {

					uploadFileService.delete(pet.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				pet.setFoto(uniqueFilename);
			}
			mS.insert(pet);
		}
		model.addAttribute("listaMascotas",mS.list());
		return "redirect:/pets/list";
	}
	
	@GetMapping("/list")
	public String listPets(Model model) {
		try {
			model.addAttribute("pet", new Pet());
			model.addAttribute("listaMascotas",mS.list());
		}catch(Exception e) {
			System.out.println("Error al listar mascotas en el controller");
		}
		return "/pet/listPet";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Pet> objAr = mS.searchId(id);
		if (objAr == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/pets/list";
		} else {
			model.addAttribute("listaClientes", cS.list());
			model.addAttribute("pet", objAr.get());
			return "pet/upet";
		}
	}
	
	@RequestMapping("/find")
	public String findBynamePet(Model model, @Validated Pet pet) throws ParseException{
		
		List<Pet> listaMascotas;
		listaMascotas=mS.findBynamePet(pet.getName());
		
		if (listaMascotas.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró la mascota");	
		}
		model.addAttribute("listaMascotas", listaMascotas);
		return "/pet/listPet";
	}
	
	@PostMapping("/update")
    public String updatePet(@Valid Pet pet, BindingResult result, Model model,
            SessionStatus status) throws Exception {
        if (result.hasErrors()) {
            return "pet/pet";
        } else {
            mS.insert(pet);
        }
        model.addAttribute("listaClientes", cS.list());
        model.addAttribute("listaMascotas", mS.list());	
        return "redirect:/pets/list";
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

		Optional<Pet> pet = mS.searchId(id);
		if (pet == null) {
			flash.addFlashAttribute("error", "La mascota no existe en la base de datos");
			return "redirect:/pets/list";
		}

		model.addAttribute("pet", pet.get());

		return "pet/view";
	}
	@RequestMapping("/delete/{id}")
	public String deletePet(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				mS.delete(id);
				model.addAttribute("listaClientes", cS.list());
				model.addAttribute("pet", new Pet());
				model.addAttribute("mensaje", "Se eliminó correctamente");

			}
			return "redirect:/pets/list";

		} catch (Exception e) {
			model.addAttribute("pet", new Pet());

			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar una mascota relacionada");
			model.addAttribute("listaClientes", cS.list());

			return "pet/listPet";
		}

	}
}
