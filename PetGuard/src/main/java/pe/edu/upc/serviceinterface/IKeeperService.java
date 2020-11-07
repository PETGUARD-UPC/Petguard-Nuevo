package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Keeper;

public interface IKeeperService {

	public void insert(Keeper keeper);

	List<Keeper> list();
	
	List<Keeper>findBynameKeeper(String nameKeeper);


}
