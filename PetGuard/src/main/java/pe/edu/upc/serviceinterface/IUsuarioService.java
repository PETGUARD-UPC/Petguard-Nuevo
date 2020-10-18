package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Usuario;

public interface IUsuarioService {
	
	public void insert(Usuario user);
	public List<Usuario> list();
	public void update(Usuario user);

}
