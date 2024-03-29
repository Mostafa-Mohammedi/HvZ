package no.noroff.HvZ.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.SquadMemberMapper;
import no.noroff.HvZ.models.SquadMember;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.squadMember.SquadMemberDTO;
import no.noroff.HvZ.models.dto.squadMember.SquadMemberPostDTO;
import no.noroff.HvZ.models.dto.squadMember.SquadMemberUpdateDTO;
import no.noroff.HvZ.services.squadMember.SquadMemberService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/squadMembers")
public class SquadMemberController {
    private SquadMemberService squadMemberService;
    private SquadMemberMapper squadMemberMapper;

    public SquadMemberController(SquadMemberService squadMemberService, SquadMemberMapper squadMemberDTO) {
        this.squadMemberService = squadMemberService;
        this.squadMemberMapper = squadMemberDTO;
    }

    @GetMapping()
    @Operation(summary = " Gets all squad members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got a list of squadMember", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SquadMemberDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "could not find anny squadMember",  content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity getAll(){
        return ResponseEntity.ok(squadMemberMapper.squadMemberToSquadMemberDTOList(squadMemberService.findAll()));
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets squad member by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the right squad member", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SquadMemberDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Could not find the squad member",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity getById(@PathVariable Integer id){
        return ResponseEntity.ok(squadMemberMapper.squadMemberToSquadMemberDTO(squadMemberService.findById(id)));
    }


    @PostMapping()
    @Operation(summary = "Adds a new squad member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created a squad member", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SquadMemberPostDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "Generic error, unexpected condition was encountered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity addSquadMember(@RequestBody SquadMemberPostDTO squadMemberPostDTO){
        SquadMember squadMember = squadMemberMapper.squadMemberDTOPostSquadMember(squadMemberPostDTO);
        squadMemberService.add(squadMember);
        URI uri = URI.create("api/v1/squadMembers/" + squadMember.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    @Operation(summary = "Updates squad member by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "update the rank of the Squad member",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SquadMemberPostDTO.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Could not find the squad member",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
            })
    public ResponseEntity updateSquadMember(@PathVariable Integer id, @RequestBody SquadMemberUpdateDTO squadMemberUpdate){
        if(!Objects.equals(id, squadMemberUpdate.getId())){
            return ResponseEntity.badRequest().build();
        }
        else {
            squadMemberService.update(squadMemberMapper.squadMemberUpdateDTo(squadMemberUpdate));
            return ResponseEntity.noContent().build();
        }
    }


    @Operation(summary = "Deletes squad member by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SquadMemberDTO.class))
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
        squadMemberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
