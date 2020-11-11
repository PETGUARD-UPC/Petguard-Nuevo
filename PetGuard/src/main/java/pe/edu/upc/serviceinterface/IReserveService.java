package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Reserve;

public interface IReserveService {
	
	public void insert(Reserve reserve);
	public List<Reserve> list();
	
	List<Reserve>findBycustomer(String name);
	//para eliminar y actualizar
	public void delete(int idReserve);
	Optional<Reserve>searchId(int idReserve);

}
