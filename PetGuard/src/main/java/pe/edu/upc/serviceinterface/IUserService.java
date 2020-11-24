package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Users;

public interface IUserService {
	
	public int insert(Users user);
	public List<Users> list();
	List<Users>findBynameUser(String username);
	public void delete(int idUser);
	Optional<Users> searchId(int idUser);
}
