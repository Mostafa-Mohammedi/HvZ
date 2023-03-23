package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.MissionMapper;
import no.noroff.HvZ.models.Mission;
import no.noroff.HvZ.models.dto.mission.MissionDTO;
import no.noroff.HvZ.models.dto.mission.MissionPostDTO;
import no.noroff.HvZ.models.dto.mission.MissionPutDTO;
import no.noroff.HvZ.repositories.MissionRepository;
import no.noroff.HvZ.services.mission.MissionService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
@CrossOrigin("*")

@RestController
@RequestMapping(path = "api/v1/missions")
public class MissionController {
    private MissionMapper missionMapper;
    private MissionService missionService;

    public MissionController(MissionMapper missionMapper, MissionService missionService) {
        this.missionMapper = missionMapper;
        this.missionService = missionService;
    }

    @Operation(summary = "Gets mission by ID")
    @ApiResponses (value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched mission with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MissionDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Mission not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )

    })

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        return ResponseEntity.ok(missionMapper.missionToMissionDTO(missionService.findById(id)));
    }


    @GetMapping
    @Operation(summary = "Gets all mission")
    @ApiResponses (value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all missions",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MissionDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Missions not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )

    })
    public ResponseEntity findAll(){
        return ResponseEntity.ok(missionMapper.missionToMissionDTOList(missionService.findAll()));
    }

    @PostMapping
    @Operation(summary = "Adds a new mission")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully added new mission",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MissionDTO.class))
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
    public ResponseEntity add(@RequestBody MissionPostDTO missionPostDTO) throws URISyntaxException {
        Mission mission = missionMapper.missionPostDTO(missionPostDTO);
        missionService.add(mission);
        URI uri  = new URI("api/v1/missions/" + mission.getId());
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("{id}")
    @Operation(summary = "Updates mission by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated mission with given ID"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Mission not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )

    })
    public ResponseEntity update(@RequestBody MissionPutDTO missionPutDTO, @PathVariable Integer id){
        System.out.println(id);
        System.out.println(missionPutDTO.getId());
        if(id != missionPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            missionService.update(missionMapper.missionPutDTO(missionPutDTO));
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(summary = "Deletes mission by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted mission with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MissionDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Mission not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        missionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
