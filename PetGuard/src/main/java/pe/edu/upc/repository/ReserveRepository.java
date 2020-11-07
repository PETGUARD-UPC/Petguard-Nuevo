package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer>{

}
