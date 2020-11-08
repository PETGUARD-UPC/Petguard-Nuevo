package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public void insert(Pay pay) {
		
		pR.save(pay);
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
	
	
}
