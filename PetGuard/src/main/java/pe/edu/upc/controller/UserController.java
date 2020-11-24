package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import pe.edu.upc.entity.Users;
import pe.edu.upc.serviceinterface.IUserService;

@Controller
@Secured({"ROLE_ADMIN","ROLE_USER"})
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService uS;

	@GetMapping("/new")
	public String newUser(Model model) {

		model.addAttribute("user", new Users());
		return "user/user";
	}
	
	@PostMapping("/save")
	public String saveUser(@Valid Users user, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "user/user";
		}
		int rpta = uS.insert(user);
		if (rpta > 0) {
			model.addAttribute("mensaje", "Ya existe el nombre de usuario");
			return "/user/user";
		} else {
			String password = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(password);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}

		model.addAttribute("listaUsuarios", uS.list());
		return "redirect:/users/list";

	}

	@GetMapping("/list")
	public String listUsers(Model model) {
		try {
			model.addAttribute("user", new Users());
			model.addAttribute("listaUsuarios", uS.list());
		} catch (Exception e) {
			System.out.println("no se pudo listar los usuarios en el controller");
		}
		return "/user/listUser";

	}

	@RequestMapping("/find")
	public String findBynameUser(Model model, @Validated Users user) throws ParseException {

		List<Users> listaUsuarios;
		listaUsuarios = uS.findBynameUser(user.getUsername());

		if (listaUsuarios.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró Usuario");
		}
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "/user/listUser";
	}
	
	@RequestMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable(value = "id") int id) {

        try {
            if(id>0) {
                uS.delete(id);
            }
            model.addAttribute("user", new Users());
            model.addAttribute("mensaje", "Se eliminó correctamente");
            model.addAttribute("listaUsuarios", uS.list());


        } catch (Exception e) {

            model.addAttribute("user", new Users());
            model.addAttribute("mensaje", "No se puede eliminar!");
            model.addAttribute("listaUsuarios", uS.list());


        }
        return  "user/listUser";
    }
	
	@GetMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Optional<Users> objUs = uS.searchId(id);

		if (objUs == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/users/list";
		} else {

			model.addAttribute("user", objUs.get());
			return "user/uuser";
		}
	}
	
	@PostMapping("/update")
	public String updateLaboratory(@Valid Users user, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "user/user";
		} else {
			uS.insert(user);
		}
		model.addAttribute("listaUsuarios", uS.list());
		return "redirect:/users/list";
	}

}
