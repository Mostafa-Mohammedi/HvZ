package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.SquadMapper;
import no.noroff.HvZ.models.dto.GameDTO;
import no.noroff.HvZ.models.dto.SquadDTO;
import no.noroff.HvZ.services.squad.SquadService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/squads")
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
}
