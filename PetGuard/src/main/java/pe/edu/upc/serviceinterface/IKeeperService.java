package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Keeper;

public interface IKeeperService {

	public int insert(Keeper keeper);
	
	public int searchE (Keeper keeper);
	List<Keeper> list();
	
	List<Keeper>findBynameKeeper(String nameKeeper);


}
