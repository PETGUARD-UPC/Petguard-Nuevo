package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Cliente;

public interface IClienteService {

	public void insert(Cliente clie);

	List<Cliente> list();

}
