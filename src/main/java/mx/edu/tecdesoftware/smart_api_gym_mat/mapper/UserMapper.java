package mx.edu.tecdesoftware.smart_api_gym_mat.mapper;

import mx.edu.tecdesoftware.smart_api_gym_mat.domain.User;
import mx.edu.tecdesoftware.smart_api_gym_mat.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "email", target = "email")
    })
    User toUser(Usuario usuario);

    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(target = "rutinas", ignore = true)
    Usuario toUsuario(User user);
}
