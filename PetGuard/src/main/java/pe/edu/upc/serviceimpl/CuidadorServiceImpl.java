package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Cuidador;
import pe.edu.upc.repository.CuidadorRepository;
import pe.edu.upc.serviceinterface.ICuidadorService;

@Service
public class CuidadorServiceImpl implements ICuidadorService {

	@Autowired
	private CuidadorRepository cR;

	@Override
	public void insert(Cuidador cuid) {
		// TODO Auto-generated method stub
		cR.save(cuid);
	}

	@Override
	public List<Cuidador> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

}
