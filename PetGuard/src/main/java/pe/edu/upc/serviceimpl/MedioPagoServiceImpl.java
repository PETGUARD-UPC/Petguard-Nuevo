package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.MedioPago;
import pe.edu.upc.repository.MedioPagoRepository;
import pe.edu.upc.serviceinterface.IMedioPagoService;

@Service
public class MedioPagoServiceImpl implements IMedioPagoService{

	@Autowired
	public MedioPagoRepository pR;

	@Override
	@Transactional
	public void insert(MedioPago mediopago) {
		
		pR.save(mediopago);
	}

	@Override
	public List<MedioPago> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}
	
	
}
