package mx.edu.tecdesoftware.smart_api_gym_mat.mapper;

import mx.edu.tecdesoftware.smart_api_gym_mat.domain.Exercise;
import mx.edu.tecdesoftware.smart_api_gym_mat.entity.Ejercicio;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MuscleCategoryMapper.class, RoutineMapper.class})
public interface ExerciseMapper {

    @Mappings({
            @Mapping(source = "id", target = "exerciseId"),
            @Mapping(source = "nombreEjercicio", target = "name"),
            @Mapping(source = "repeticiones", target = "repetitions"),
            @Mapping(source = "categoriaMuscular", target = "muscleCategory"),
            @Mapping(source = "rutina", target = "routine")
    })
    Exercise toExercise(Ejercicio ejercicio);

    List<Exercise> toExercises(List<Ejercicio> ejercicios);

    @InheritInverseConfiguration
    Ejercicio toEjercicio(Exercise exercise);
}
