package mx.edu.tecdesoftware.smart_api_gym_mat.repository;

import mx.edu.tecdesoftware.smart_api_gym_mat.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}