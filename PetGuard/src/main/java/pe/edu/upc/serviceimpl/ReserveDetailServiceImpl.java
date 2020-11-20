package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.ReserveDetail;
import pe.edu.upc.repository.ReserveDetailRepository;
import pe.edu.upc.serviceinterface.IReserveDetailService;

@Service
public class ReserveDetailServiceImpl implements IReserveDetailService{

	@Autowired
	private ReserveDetailRepository idRe;
	
	@Override
	public Integer insert(ReserveDetail rsdt) {
		ReserveDetail rsdte = idRe.save(rsdt);
		if (rsdte == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public void delete(int idReserveDetail) {
		// TODO Auto-generated method stub
		idRe.deleteById(idReserveDetail);
	}

}
