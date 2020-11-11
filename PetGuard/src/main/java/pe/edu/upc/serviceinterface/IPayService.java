package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Pay;

public interface IPayService {

	public int insert(Pay pay);
	
	public List<Pay>list();
	
	List<Pay>findBynamePay(String namePay);
	
	public void delete(int idPay);
	
	Optional<Pay> searchId(int idPay);
}
