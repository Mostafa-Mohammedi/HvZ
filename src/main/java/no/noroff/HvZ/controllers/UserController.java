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
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @GetMapping()
    @Operation(summary = "Gets all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all user",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity findAllUser() {
        return ResponseEntity.ok(userMapper.usertoUserDTOList(userService.findAll()));
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched user by given ID",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(userMapper.userToUserDTO(userService.findById(id)));
    }


    @PostMapping()
    @Operation(summary = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created a new user",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Generic error, unexpected condition was encountered",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
                    }
            )
    })
    public ResponseEntity add(@RequestBody PostUserDTO postUserDTO) throws URISyntaxException {
        User user = userMapper.postUserToDTO(postUserDTO);
        userService.add(user);
        URI uri = new URI("api/v1/user" + user.getId());
        return ResponseEntity.created(uri).build();
    }
    
    
    @PutMapping("{id}")
    @Operation(summary = "Update a user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated user by id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
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

    @GetMapping("/token/{idToken}")
    public ResponseEntity findUserByIdToken(@PathVariable String idToken){
        User user = userService.findByIdToken(idToken);
        if (user == null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userMapper.userToUserDTO(userService.findByIdToken(idToken)));
        }
    }

    @Operation(summary = "Deletes user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted user by id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
         userService.deleteById(id);
         return ResponseEntity.noContent().build();
    }



}
