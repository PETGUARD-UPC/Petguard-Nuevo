package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Keeper;
import pe.edu.upc.repository.KeeperRepository;
import pe.edu.upc.serviceinterface.IKeeperService;

@Service
public class KeeperServiceImpl implements IKeeperService {

	@Autowired
	private KeeperRepository kR;



	@Override
	public List<Keeper> list() {
		// TODO Auto-generated method stub
		return kR.findAll();
	}

	@Override
	public List<Keeper> findBynameKeeper(String nameKeeper) {
		
		return kR.findBynameKeeper(nameKeeper);
	}

	@Override
	public int insert(Keeper keeper) {
		int rpta= kR.searchKeeper(keeper.getDni()) ;
		if(rpta==0) {
			kR.save(keeper);
		}
		return rpta;
	}

	@Override
	public int searchE(Keeper keeper) {
		int rptaE= kR.searchKeeper(keeper.getEmail()) ;
		if(rptaE==0) {
			kR.save(keeper);
		}
		return rptaE;
	}
	
	@Override
	public void delete(int idkeeper) {
		// TODO Auto-generated method stub
		kR.deleteById(idkeeper);
	}
	
	@Override
	public Optional<Keeper> searchId(int idkeeper) {
		// TODO Auto-generated method stub
		return kR.findById(idkeeper);
	}

	@Override
	public List<String[]> keeperTop() {
		// TODO Auto-generated method stub
		return kR.keeperTop();
	}

}
