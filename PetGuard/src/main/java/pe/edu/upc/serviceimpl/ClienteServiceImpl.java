package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Cliente;
import pe.edu.upc.repository.ClienteRepository;
import pe.edu.upc.serviceinterface.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository cR;

	@Override
	public void insert(Cliente clie) {
		// TODO Auto-generated method stub
		cR.save(clie);
	}

	@Override
	public List<Cliente> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}


}
