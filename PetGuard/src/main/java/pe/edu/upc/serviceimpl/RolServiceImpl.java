package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Role;
import pe.edu.upc.repository.RolRepository;
import pe.edu.upc.serviceinterface.IRolService;

@Service
public class RolServiceImpl implements IRolService, Serializable{

	private static final long serialVersionUID = 1L;
	
	//llamar al repositorio de Rol
	@Autowired
	private RolRepository roR;
	
	@Override
	@Transactional
	public void insertar(Role rol) {
		// TODO Auto-generated method stub
		try {
			roR.save(rol);
		} catch (Exception e) {
			System.out.println("Error al insertar el rol");
		}
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return roR.findAll();
	}
}
