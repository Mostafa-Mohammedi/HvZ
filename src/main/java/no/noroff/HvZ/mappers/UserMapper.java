package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.dto.user.PostUserDTO;
import no.noroff.HvZ.models.dto.user.UpdateUserDTO;
import no.noroff.HvZ.models.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "players", source = "players", qualifiedByName = "subjectsToIds")

     UserDTO userToUserDTO(User user);
     Collection <UserDTO> usertoUserDTOList(Collection<User> user);
     User postUserToDTO(PostUserDTO postUserDTO);
     User updateUserToDTO(UpdateUserDTO updateUserDTO);





    @Named("subjectsToIds")
    default Set<Integer> map(Set<Player> testing){
        if( testing == null){
            return null;
        }
        return testing.stream()
                .map(t -> t.getId())
        .collect(Collectors.toSet());
    }

}
