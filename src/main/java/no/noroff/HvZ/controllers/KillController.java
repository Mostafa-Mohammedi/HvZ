package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.KillMapper;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.kill.KillPostDTO;
import no.noroff.HvZ.models.dto.kill.KillUpdateDTO;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.services.kill.KillService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/kills")
public class KillController {
    private KillService killService;
    private KillMapper killMapper;
    private GameRepository gameRepository;

    public KillController(KillService killService, KillMapper killMapper, GameRepository gameRepository) {
        this.killService = killService;
        this.killMapper = killMapper;
        this.gameRepository = gameRepository;

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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Kill not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(killMapper.killMappedToKillDTO(killService.findAll()));
    }

    @Operation(summary = "Adds a new kill")
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
                    responseCode = "500",
                    description = "Generic error, unexpected condition was encountered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PostMapping
    public ResponseEntity add(@RequestBody KillPostDTO killDTO){
        Kill kill = killService.add(killMapper.killPostDtoToKill(killDTO));
        URI location = URI.create("api/v1/players/" + kill.getId());
        return ResponseEntity.created(location).build();
    }


    @Operation(summary = "Deletes kill by ID")
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
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        killService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Updates kill by ID")
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
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody KillUpdateDTO killDTO, @PathVariable Integer id){
        if (id != killDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        killService.update(killMapper.killUpdateDtoToKill(killDTO));

        return ResponseEntity.noContent().build();
    }

}
