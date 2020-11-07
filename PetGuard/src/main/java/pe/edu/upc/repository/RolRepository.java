package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
