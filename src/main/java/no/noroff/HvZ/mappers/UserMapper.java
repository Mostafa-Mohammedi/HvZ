package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.dto.user.PostUserDTO;
import no.noroff.HvZ.models.dto.user.UpdateUserDTO;
import no.noroff.HvZ.models.dto.user.UserDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDTO userToUserDTO(User user);
    public abstract Collection <UserDTO> usertoUserDTOList(Collection<User> user);
    public abstract User postUserToDTO(PostUserDTO postUserDTO);
    public abstract User updateUserToDTO(UpdateUserDTO updateUserDTO);


}
