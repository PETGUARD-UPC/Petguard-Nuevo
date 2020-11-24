package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer>{

	@Query(value = "SELECT i.date,count(ide.id_reserve) from public.reserves i join reservedetails ide on  ide.id_reserve = i.id_reserve group by i.date ORDER BY COUNT(ide.id_reserve)", nativeQuery = true)
	public List<String[]> reserveMonth();
	
	/*
	 * @Query("from Reserve r where r.customer.name like %?1% or r.keeper.name like %?1% or r.keeper.lastname like %?1% or r.customer.lastname like %?1% or UPPER(r.customer.name) like %?1% or LOWER(r.customer.name) like %?1% or UPPER(r.keeper.name) like %?1% or LOWER(r.keeper.name) like %?1% or UPPER(r.keeper.lastname) like %?1% or LOWER(r.keeper.lastname) like %?1% or UPPER(r.customer.lastname) like %?1% or LOWER(r.customer.lastname) like %?1%"
	 * ) List<Reserve>findBycustomer(String name);
	 */
	@Query(value="SELECT i.date,max(ide.hour) from public.reserves i join reservedetails ide on  ide.id_reserve = i.id_reserve group by i.date ORDER BY COUNT(ide.hour) ASC limit 5", nativeQuery = true)
	public List<String[]> reserveHour();
}
//@Query("from Pay p where p.name like %:name% or p.banking.name like %:name% or UPPER(p.name) like %:name% or LOWER(p.name) like %:name% or UPPER(p.banking.name) like %:name% or LOWER(p.banking.name) like %:name%")