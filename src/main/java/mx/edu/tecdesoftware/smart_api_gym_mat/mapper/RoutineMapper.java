package mx.edu.tecdesoftware.smart_api_gym_mat.mapper;

import mx.edu.tecdesoftware.smart_api_gym_mat.domain.Routine;
import mx.edu.tecdesoftware.smart_api_gym_mat.entity.Rutina;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoutineMapper {

    @Mappings({
            @Mapping(source = "id", target = "routineId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "objetivo", target = "objective"),
            @Mapping(source = "usuario", target = "user")
    })
    Routine toRoutine(Rutina rutina);

    List<Routine> toRoutines(List<Rutina> rutinas);

    @InheritInverseConfiguration
    @Mapping(target = "ejercicios", ignore = true)
    Rutina toRutina(Routine routine);
}
