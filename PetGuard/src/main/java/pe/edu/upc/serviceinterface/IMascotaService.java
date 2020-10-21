package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Mascota;

public interface IMascotaService {
	
	public void insert (Mascota pet);
	public List<Mascota> list();

}
