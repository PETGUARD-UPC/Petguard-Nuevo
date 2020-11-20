package pe.edu.upc.serviceinterface;

import pe.edu.upc.entity.ReserveDetail;

public interface IReserveDetailService {
	public Integer insert(ReserveDetail rsdt);

	public void delete(int idReserveDetail);
}
