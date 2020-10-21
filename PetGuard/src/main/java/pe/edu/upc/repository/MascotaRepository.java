package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Mascota;
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer>{

}
