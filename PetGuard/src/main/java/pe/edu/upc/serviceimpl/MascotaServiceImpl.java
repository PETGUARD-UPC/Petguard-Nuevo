package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Mascota;
import pe.edu.upc.repository.MascotaRepository;
import pe.edu.upc.serviceinterface.IMascotaService;

@Service
public class MascotaServiceImpl implements IMascotaService{

	@Autowired
	private MascotaRepository mR;
	
	@Override
	public void insert(Mascota pet) {
			mR.save(pet);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Mascota> list() {
		// TODO Auto-generated method stub
		return mR.findAll();
	}

}
