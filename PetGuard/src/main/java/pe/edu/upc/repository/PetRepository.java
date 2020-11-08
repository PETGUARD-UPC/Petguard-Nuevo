package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Pet;
@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{

	@Query("from Pet p where p.name like %:name% or p.breed like %:name%")
	List<Pet>findBynamePet(@Param("name")String namePet);
}
