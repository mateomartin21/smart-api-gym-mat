package mx.edu.tecdesoftware.smart_api_gym_mat.mapper;

import mx.edu.tecdesoftware.smart_api_gym_mat.domain.MuscleCategory;
import mx.edu.tecdesoftware.smart_api_gym_mat.entity.CategoriaMuscular;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuscleCategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "muscleCategoryId"),
            @Mapping(source = "nombre", target = "name")
    })
    MuscleCategory toMuscleCategory(CategoriaMuscular categoriaMuscular);

    List<MuscleCategory> toMuscleCategories(List<CategoriaMuscular> categoriasMusculares);

    @InheritInverseConfiguration
    @Mapping(target = "ejercicios", ignore = true)
    CategoriaMuscular toCategoriaMuscular(MuscleCategory muscleCategory);
}
