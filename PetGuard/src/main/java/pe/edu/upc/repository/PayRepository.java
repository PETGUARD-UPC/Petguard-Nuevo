package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer>{

}
