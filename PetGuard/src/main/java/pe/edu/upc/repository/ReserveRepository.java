package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer>{
	
	@Query("from Reserve r where r.customer.name like %?1% or r.keeper.name like %?1% or r.keeper.lastname like %?1% or r.customer.lastname like %?1% ")
	List<Reserve>findBycustomer(String name);
	

}
