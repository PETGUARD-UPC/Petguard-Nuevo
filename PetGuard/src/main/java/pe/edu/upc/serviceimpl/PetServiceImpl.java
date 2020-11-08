package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Pet;
import pe.edu.upc.repository.PetRepository;
import pe.edu.upc.serviceinterface.IPetService;

@Service
public class PetServiceImpl implements IPetService{

	@Autowired
	private PetRepository pR;
	
	@Override
	public void insert(Pet pet) {
			pR.save(pet);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pet> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	public List<Pet> findBynamePet(String namePet) {
		// TODO Auto-generated method stub
		return pR.findBynamePet(namePet);
	}

}
