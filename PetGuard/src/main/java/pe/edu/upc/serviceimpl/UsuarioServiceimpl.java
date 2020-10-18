package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Usuario;
import pe.edu.upc.repository.UsuarioRepository;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceimpl implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository uR;
	
	@Override
	public void insert(Usuario user) {
		uR.save(user);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}

	@Override
	public void update(Usuario user) {
		// TODO Auto-generated method stub
		
	}

}
