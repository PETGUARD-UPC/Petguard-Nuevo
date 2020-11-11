package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer> {
	@Query("from Pay p where p.name like %:name% or p.entity like %:name%")
	List<Pay> findBynamePay(@Param("name") String namePay);

	@Query("select count(p.name) from Pay p where p.name=:name")
	public int searchPayName(@Param("name") String namePay);

	@Query("select count(p.entity) from Pay p where p.entity=:entity")
	public int searchPayEntity(@Param("entity") String entityPay);

}
