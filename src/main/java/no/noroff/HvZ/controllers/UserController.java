package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.UserMapper;
import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.dto.user.PostUserDTO;
import no.noroff.HvZ.models.dto.user.UpdateUserDTO;
import no.noroff.HvZ.models.dto.user.UserDTO;
import no.noroff.HvZ.services.User.UserService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getAllUser")
    @Operation(summary = "Gets all the user")
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


    @PostMapping()
    @Operation(summary = "added user for HvZ game")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "created a user",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Could not find user",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
                    }
            )
    })
    public ResponseEntity addUser(@RequestBody PostUserDTO postUserDTO) throws URISyntaxException {
        User user = userMapper.postUserToDTO(postUserDTO);
        userService.add(user);
        URI uri = new URI("api/v1/users" + user.getId());
       return ResponseEntity.created(uri).build();
    }
    @PutMapping("{id}")
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "update user"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Could not find user",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )

    })
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO updateUserDTO, @PathVariable int id){
        if(id != updateUserDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else{
            userService.update(userMapper.updateUserToDTO(updateUserDTO));
            return ResponseEntity.noContent().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
         userService.deleteById(id);
         return ResponseEntity.noContent().build();
    }



}
