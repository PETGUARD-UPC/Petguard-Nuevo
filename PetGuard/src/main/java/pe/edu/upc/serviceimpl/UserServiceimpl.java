package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.User;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.serviceinterface.IUserService;

@Service
public class UserServiceimpl implements IUserService{
	
	@Autowired
	private UserRepository uR;
	
	@Override
	public void insert(User user) {
		uR.save(user);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}

	@Override
	public List<User> findBynameUser(String username) {
		// TODO Auto-generated method stub
		return uR.findBynameUser(username);
	}


}
