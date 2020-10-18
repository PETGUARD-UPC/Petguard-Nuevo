package pe.edu.upc.serviceimplement;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Rol;
import pe.edu.upc.repository.rolRepository;
import pe.edu.upc.serviceinterface.IRolService;

@Service
public class RolServiceImpl implements IRolService, Serializable{

	private static final long serialVersionUID = 1L;
	
	//llamar al repositorio de Rol
	@Autowired
	private rolRepository rR;
	
	@Override
	@Transactional
	public void insertar(Rol rol) {
		// TODO Auto-generated method stub
		try {
			rR.save(rol);
		} catch (Exception e) {
			System.out.println("Error al insertar el rol");
		}
	}

	@Override
	public List<Rol> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	@Transactional
	public void delete(int idRol) {
		// TODO Auto-generated method stub
		try {
			rR.deleteById(idRol);
		} catch (Exception e) {
			System.out.println("Error al eliminar el rol por id");
		}
	}

}
