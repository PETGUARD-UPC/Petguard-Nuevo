package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Reserva;

public interface IReservaService {
	
	public void insert(Reserva res);
	public List<Reserva> list();

}
