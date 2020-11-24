package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Keeper;

@Repository
public interface KeeperRepository extends JpaRepository<Keeper, Integer>{

	@Query("from Keeper k  where k.name like %:name% or k.lastname like %:name% or UPPER(k.name) like %:name% or LOWER(k.name) like %:name% or UPPER(k.lastname) like %:name% or LOWER(k.lastname) like %:name%")
	List<Keeper>findBynameKeeper(@Param("name")String nameKeeper);
	
	@Query("select count(k.name)from Keeper k where k.dni =:dni or LOWER(k.dni)=:dni")
	public int searchKeeper(@Param("dni")String nombre);
	
	@Query("select count(k.name)from Keeper k where k.email =:email")
	public int searchKeeperEmail(@Param("email")String nombre);
	
	@Query(value = "SELECT k.name,count(re.id_reserve) from public.reserves i join reservedetails ide on  ide.id_reserve = i.id_reserve join reserves re on ide.id_reserve = re.id_reserve join keepers k on k.id_keeper= k.id_keeper group by k.name ORDER BY COUNT(k.name) DESC limit 5", nativeQuery = true)
	public List<String[]> keeperTop();
}
