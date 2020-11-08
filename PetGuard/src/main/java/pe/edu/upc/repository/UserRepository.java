package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User u where u.username like %:username%")
	List<User>findBynameUser(@Param("username")String nameUser);
	
	@Query("select count(u.username) from User u where u.username=:username or LOWER(u.username)=:username")
	public int searchUser(@Param("username")String username);

}
