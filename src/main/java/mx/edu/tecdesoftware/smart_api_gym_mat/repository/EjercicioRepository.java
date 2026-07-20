package mx.edu.tecdesoftware.smart_api_gym_mat.repository;

import mx.edu.tecdesoftware.smart_api_gym_mat.entity.Ejercicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends CrudRepository<Ejercicio, Integer> {
}