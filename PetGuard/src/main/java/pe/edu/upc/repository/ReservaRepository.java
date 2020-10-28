package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

}
