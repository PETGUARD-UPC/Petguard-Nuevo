package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.ReserveDetail;

@Repository
public interface ReserveDetailRepository extends JpaRepository<ReserveDetail, Integer>{

	
	
}
