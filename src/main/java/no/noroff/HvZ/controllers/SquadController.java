package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.SquadMapper;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.dto.chat.ChatDTO;
import no.noroff.HvZ.models.dto.chat.ChatPutDTO;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.player.PlayerPostDTO;
import no.noroff.HvZ.models.dto.player.PlayerUpdateDTO;
import no.noroff.HvZ.models.dto.squad.SquadDTO;
import no.noroff.HvZ.models.dto.squad.SquadPostDTO;
import no.noroff.HvZ.models.dto.squad.SquadPutDTO;
import no.noroff.HvZ.services.squad.SquadService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/squad")
public class SquadController {


    private final SquadService squadService;
    private final SquadMapper squadMapper;

    public SquadController(SquadService squadService, SquadMapper squadMapper) {
        this.squadService = squadService;
        this.squadMapper = squadMapper;
    }

    @Operation(summary = "Gets squad by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SquadDTO.class))
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
        return ResponseEntity.ok(squadMapper.squadToSquadDTO(squadService.findById(id)));
    }

    @Operation(summary = "Gets all squads")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SquadDTO.class)))
                    }
            )
    })

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(squadMapper.squadToSquadDTO(squadService.findAll()));

    }

    @PostMapping
    @Operation(summary = "Create Squad")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SquadDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity add(@RequestBody SquadPostDTO squadPostDTO){
        Squad squad = squadService.add(squadMapper.squadPostDTO(squadPostDTO));
        URI location = URI.create("api/v1/squad/" + squad.getId());
        return ResponseEntity.created(location).build();
    }



    @PutMapping("{id}")
    @Operation(summary = "Update Chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "update squad",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SquadDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity update(@RequestBody SquadPutDTO squadPutDTO, @PathVariable Integer id){
        if(id != squadPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            squadService.update(squadMapper.squadPutDTO(squadPutDTO));
            return ResponseEntity.noContent().build();
        }
    }



    @Operation(summary = "Deletes a Squad by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SquadDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        squadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
