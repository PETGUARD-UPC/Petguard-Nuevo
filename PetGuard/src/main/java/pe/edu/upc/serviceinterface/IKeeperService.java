package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Keeper;

public interface IKeeperService {

	public int insert(Keeper keeper);
	
	public int searchE (Keeper keeper);
	
	public List<Keeper> list();
	
	public void delete(int idKeeper);
	
	List<Keeper>findBynameKeeper(String nameKeeper);
	
	Optional<Keeper> searchId(int idKeeper);


}
