package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    Collection <UserDTO> usertoUserDTOList(Collection<User> user);


}
