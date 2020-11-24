package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query("from Users u where UPPER(u.username) like %:username% or LOWER(u.username) like %:username%")
	List<Users>findBynameUser(@Param("username")String nameUser);
	
	@Query("select count(u.username) from Users u where u.username=:username or LOWER(u.username)=:username")
	public int searchUser(@Param("username")String username);
	
	public Users findByUsername(String username);

}
