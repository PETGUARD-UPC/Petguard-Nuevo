package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.User;

public interface IUserService {
	
	public void insert(User user);
	public List<User> list();
}
