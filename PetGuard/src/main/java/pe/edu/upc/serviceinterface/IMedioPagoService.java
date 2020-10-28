package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.MedioPago;

public interface IMedioPagoService {

	public void insert(MedioPago mediopago);
	
	List<MedioPago>list();
	
}
