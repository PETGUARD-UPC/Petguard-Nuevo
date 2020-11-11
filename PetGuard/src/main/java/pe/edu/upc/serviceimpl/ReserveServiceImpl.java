package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Reserve;
import pe.edu.upc.repository.ReserveRepository;
import pe.edu.upc.serviceinterface.IReserveService;

@Service
public class ReserveServiceImpl implements IReserveService{

	@Autowired
	private ReserveRepository rR;
	
	@Override
	public void insert(Reserve reserve) {
		rR.save(reserve);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reserve> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public List<Reserve> findBycustomer(String name) {
		// TODO Auto-generated method stub
		return rR.findBycustomer(name);
	}

	@Override
	public void delete(int idReserve) {
		rR.deleteById(idReserve);
		
	}

	@Override
	public Optional<Reserve> searchId(int idReserve) {
		
		return rR.findById(idReserve);
	}

}
