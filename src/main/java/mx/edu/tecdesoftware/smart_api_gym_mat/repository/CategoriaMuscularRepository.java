package mx.edu.tecdesoftware.smart_api_gym_mat.repository;

import mx.edu.tecdesoftware.smart_api_gym_mat.entity.CategoriaMuscular;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaMuscularRepository extends CrudRepository<CategoriaMuscular, Integer> {
}