package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Role;

public interface IRolService {

	public void insertar(Role rol);
	
	List<Role>list();
}
