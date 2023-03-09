package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.KillMapper;
import no.noroff.HvZ.models.dto.KillDTO;
import no.noroff.HvZ.services.kill.KillService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/kill")
public class KillController {
    private KillService killService;
    private KillMapper killMapper;

    public KillController(KillService killService, KillMapper killMapper) {
        this.killService = killService;
        this.killMapper = killMapper;
    }

    @Operation(summary = "Gets kill by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = KillDTO.class))
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
    public ResponseEntity findById(@PathVariable Integer id){
        return ResponseEntity.ok(killMapper.killMappedToKillDTO(killService.findById(id)));
    }


    @Operation(summary = "Gets all kills")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = KillDTO.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(killMapper.killMappedToKillDTO(killService.findAll()));
    }
}
