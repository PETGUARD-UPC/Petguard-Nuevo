package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Pay;

public interface IPayService {

	public void insert(Pay pay);
	
	List<Pay>list();
	
}
