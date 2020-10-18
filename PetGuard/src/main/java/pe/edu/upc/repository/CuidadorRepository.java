package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Cuidador;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador, Integer>{

}
