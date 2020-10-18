package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
