package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Pet;

public interface IPetService {
	
	public void insert (Pet pet);
	public List<Pet> list();
	public void delete(int idPet);
	List<Pet>findBynamePet(String namePet);
	Optional<Pet> searchId(int idPet);
	
}
