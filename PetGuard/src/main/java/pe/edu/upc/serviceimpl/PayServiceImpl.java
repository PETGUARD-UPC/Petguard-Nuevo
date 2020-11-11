package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Pay;
import pe.edu.upc.repository.PayRepository;
import pe.edu.upc.serviceinterface.IPayService;

@Service
public class PayServiceImpl implements IPayService{

	@Autowired
	public PayRepository pR;

	@Override
	public int insert(Pay pay) {
		
		pR.save(pay);
		return 0;
	}

	@Override
	public List<Pay> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}
	
	@Override
	public List<Pay> findBynamePay(String namePay) {
		// TODO Auto-generated method stub
		return pR.findBynamePay(namePay);
	}
	
	@Override
	public void delete(int idPay) {
		pR.deleteById(idPay);
	}
	
	@Override
	public Optional<Pay> searchId(int idPay) {
		// TODO Auto-generated method stub
		return pR.findById(idPay);

	}
	
}
