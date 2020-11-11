package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Banking;

public interface IBanking {
	
public int insert(Banking banking);
	
	public List<Banking>list();
	
	List<Banking>findBynameBanking(String name);
	
	public void delete(int idBanking);
	
	Optional<Banking> searchId(int idBanking);

}
