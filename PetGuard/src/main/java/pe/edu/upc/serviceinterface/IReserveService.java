package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Reserve;

public interface IReserveService {
	
	public void insert(Reserve reserve);
	public List<Reserve> list();
	
	List<Reserve>findBycustomer(String name);

}
