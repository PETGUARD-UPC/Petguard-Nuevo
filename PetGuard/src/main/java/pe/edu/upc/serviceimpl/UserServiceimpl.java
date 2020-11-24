package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

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
	public List<User> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}

	@Override
	public List<User> findBynameUser(String username) {
		// TODO Auto-generated method stub
		return uR.findBynameUser(username);
	}

	@Override
	public int insert(User user) {
		int rpta=uR.searchUser(user.getUsername());
		if (rpta==0) {
			uR.save(user);
		}
		return rpta;
	}

	@Override
	public void delete(int idUser) {
		uR.deleteById(idUser);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> searchId(int idUser) {

		return uR.findById(idUser);
	}
	
}
