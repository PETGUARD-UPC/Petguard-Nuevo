package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Customer;
import pe.edu.upc.repository.CustomerRepository;
import pe.edu.upc.serviceinterface.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository cR;

	@Override
	public int insert(Customer customer) {
		int rpta = cR.searchCustomer(customer.getDni());
		if (rpta == 0) {
			cR.save(customer);
		}
		return rpta;
	}

	@Override
	public List<Customer> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public List<Customer> findBynameCustomer(String nameCustomer) {

		return cR.findBynameCustomer(nameCustomer);
	}

	@Override
	public Optional<Customer> searchId(int idCustomer) {
		// TODO Auto-generated method stub
		return cR.findById(idCustomer);
	}

	@Override
	public void delete(int idCustomer) {
		// TODO Auto-generated method stub
		cR.deleteById(idCustomer);
	}

	@Override
	public int searchE(Customer customer) {
		int rptaE = cR.searchCustomer(customer.getEmail());
		if (rptaE == 0) {
			cR.save(customer);
		}
		return rptaE;
	}

}
