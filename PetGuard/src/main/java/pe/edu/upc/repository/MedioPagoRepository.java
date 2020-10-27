package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.MedioPago;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Integer>{

}
