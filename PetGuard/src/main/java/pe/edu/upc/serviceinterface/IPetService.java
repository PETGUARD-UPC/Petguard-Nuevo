package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Pet;

public interface IPetService {
	
	public void insert (Pet pet);
	public List<Pet> list();
	List<Pet>findBynamePet(String namePet);
}
