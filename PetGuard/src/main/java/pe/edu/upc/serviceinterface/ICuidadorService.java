package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Cuidador;

public interface ICuidadorService {

	public void insert(Cuidador cuid);

	List<Cuidador> list();


}
