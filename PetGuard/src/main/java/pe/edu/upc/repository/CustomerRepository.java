package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("from Customer c where c.name like %:name% or c.lastname like %:name% or UPPER(c.name) like %:name% or LOWER(c.name) like %:name% or UPPER(c.lastname) like %:name% or LOWER(c.lastname) like %:name%")
	List<Customer> findBynameCustomer(@Param("name") String nameCustomer);

	@Query("select count(c.dni) from Customer c where c.dni=:dni or LOWER(c.dni)=:dni")
	public int searchCustomer(@Param("dni") String dni);

	@Query("select count(c.name)from Customer c where c.email =:email")
	public int searchCustomerEmail(@Param("email") String nombre);

}
