package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.PlayerMapper;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.dto.player.PlayerCheckInDTO;
import no.noroff.HvZ.models.dto.player.PlayerDTO;
import no.noroff.HvZ.models.dto.player.PlayerPostDTO;
import no.noroff.HvZ.models.dto.player.PlayerUpdateDTO;
import no.noroff.HvZ.services.player.PlayerService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @Operation(summary = "Gets player by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PlayerDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(playerMapper.playerToPlayerDTO(playerService.findById(id)));
    }


    @Operation(summary = "Gets all players")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(playerMapper.playerToPlayerDTO(playerService.findAll()));
    }


    @Operation(summary = "Adds a new player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('roles')")
    public ResponseEntity add(@RequestBody PlayerPostDTO playerDTO){
        Player player = playerService.add(playerMapper.playerPostDtoToPlayer(playerDTO));
        URI location = URI.create("api/v1/players/" + player.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a player by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated player by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Player not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}")
    @PreAuthorize("hasRole('roles')")
    public ResponseEntity update(@RequestBody PlayerUpdateDTO playerDTO, @PathVariable Integer id){
        if (id != playerDTO.getId()){
            System.out.println(playerDTO.getId());
            return ResponseEntity.badRequest().build();
        }
        playerService.update(playerMapper.playerUpdateDtoToPlayer(playerDTO));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Gets player check in by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PlayerCheckInDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Player not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}/checkIn")
    public ResponseEntity CheckIn(PlayerCheckInDTO playerCheckInDTO, @PathVariable Integer id){
        playerService.playerCheckIn(playerMapper.playerCheckInDTOtoPlayer(playerCheckInDTO), id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Deletes player by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted player with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Player not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('roles')")
    public ResponseEntity delete(@PathVariable Integer id){
        playerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("deleteByToken/{token}")
    public ResponseEntity deleteByToken(@PathVariable String token) {
        playerService.deleteByToken(token);
        return ResponseEntity.noContent().build();
    }
}
