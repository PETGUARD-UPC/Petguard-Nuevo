package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Customer;

public interface ICustomerService {

	public void insert(Customer customer);

	public List<Customer> list();
	
	List<Customer>findBynameCustomer(String nameCustomer);

}
