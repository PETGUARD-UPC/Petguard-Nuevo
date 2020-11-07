package pe.edu.upc.serviceimpl;

import java.util.List;

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
	public void insert(Keeper keeper) {
		// TODO Auto-generated method stub
		kR.save(keeper);
	}

	@Override
	public List<Keeper> list() {
		// TODO Auto-generated method stub
		return kR.findAll();
	}

	@Override
	public List<Keeper> findBynameKeeper(String nameKeeper) {
		
		return kR.findBynameKeeper(nameKeeper);
	}

}
