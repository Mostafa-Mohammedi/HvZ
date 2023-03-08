package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.UserMapper;
import no.noroff.HvZ.models.dto.UserDTO;
import no.noroff.HvZ.services.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/getAllUser")
    @Operation(summary = "Gets all the movies in the database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful fetched all user",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                            )

                    }
            )

    })
    public ResponseEntity findAllUser() {
        return ResponseEntity.ok(userMapper.usertoUserDTOList(userService.findAll()));
    }

    @GetMapping("{id}")
    @Operation(summary = "successful fetched user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful fetched user",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                            )
                    }
            )
    })
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(userMapper.userToUserDTO(userService.findById(id)));
    }

}
