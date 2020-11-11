package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Banking;

@Repository
public interface BankingRepository extends JpaRepository<Banking, Integer>{
	
	@Query("from Banking b where b.name like %:name% or UPPER(b.name) like %:name% or LOWER(b.name) like %:name%")
	List<Banking> findBynameBanking(@Param("name") String name);
	
	@Query("select count(b.name) from Banking b where UPPER(b.name)=:name or LOWER(b.name)=:name")
	public int searchName(@Param("name") String name);

}
