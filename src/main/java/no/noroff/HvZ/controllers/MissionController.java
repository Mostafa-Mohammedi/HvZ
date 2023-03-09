package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.MissionMapper;
import no.noroff.HvZ.models.dto.MissionDTO;
import no.noroff.HvZ.services.mission.MissionService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")

@RestController
@RequestMapping(path = "api/v1/mission")
public class MissionController {
    private MissionMapper missionMapper;
    private MissionService missionService;

    public MissionController(MissionMapper missionMapper, MissionService missionService) {
        this.missionMapper = missionMapper;
        this.missionService = missionService;
    }

    @Operation(summary = "Get all mission")
    @ApiResponses (value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MissionDTO.class))
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
        return ResponseEntity.ok(missionMapper.missionToMissionDTO(missionService.findById(id)));
    }


    @GetMapping
    @Operation(summary = "Get all mission")
    @ApiResponses (value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MissionDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )

    })
    public ResponseEntity findAll(){
        return ResponseEntity.ok(missionMapper.missionToMissionDTOList(missionService.findAll()));
    }
}
