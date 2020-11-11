package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Banking;
import pe.edu.upc.repository.BankingRepository;
import pe.edu.upc.serviceinterface.IBanking;

@Service
public class BankingImpl implements IBanking {

	@Autowired
	public BankingRepository bR;
	
	@Override
	public int insert(Banking banking) {
		
		int rptaN=bR.searchName(banking.getName());
		if(rptaN ==0) {
			bR.save(banking);
		}
		return rptaN;
	}

	@Override
	public List<Banking> list() {
		// TODO Auto-generated method stub
		return bR.findAll();
	}

	@Override
	public List<Banking> findBynameBanking(String name) {
		// TODO Auto-generated method stub
		return bR.findBynameBanking(name);
	}

	@Override
	public void delete(int idBanking) {
		// TODO Auto-generated method stub
		bR.deleteById(idBanking);
		
	}

	@Override
	public Optional<Banking> searchId(int idBanking) {
		// TODO Auto-generated method stub
		return bR.findById(idBanking);
	}
	
	

}
