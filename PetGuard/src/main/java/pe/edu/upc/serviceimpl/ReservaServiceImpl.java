package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Reserva;
import pe.edu.upc.repository.ReservaRepository;
import pe.edu.upc.serviceinterface.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService{

	@Autowired
	private ReservaRepository rR;
	
	@Override
	public void insert(Reserva res) {
		rR.save(res);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reserva> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

}
