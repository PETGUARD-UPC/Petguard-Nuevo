package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Customer;

public interface ICustomerService {

	public int insert(Customer customer);

	public int searchE(Customer customer);

	public List<Customer> list();

	public void delete(int idCustomer);

	List<Customer> findBynameCustomer(String nameCustomer);

	Optional<Customer> searchId(int idCustomer);

}
