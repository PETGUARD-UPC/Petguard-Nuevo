package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.User;

public interface IUserService {
	
	public int insert(User user);
	public List<User> list();
	List<User>findBynameUser(String username);
	public void delete(int idUser);
	Optional<User> searchId(int idUser);
}
