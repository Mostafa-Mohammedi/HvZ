package no.noroff.HvZ.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.HumanChatMapper;
import no.noroff.HvZ.models.HumanChat;
import no.noroff.HvZ.models.dto.game.GameDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatPostDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatPutDTO;
import no.noroff.HvZ.services.humanChat.HumanChatService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin("*")
@RestController
@RequestMapping(path="api/v1/humanChats")
public class HumanChatController {
    private HumanChatService humanChatService;
    private HumanChatMapper humanChatMapper;

    public HumanChatController(HumanChatService humanChatService, HumanChatMapper humanChatMapper) {
        this.humanChatService = humanChatService;
        this.humanChatMapper = humanChatMapper;
    }

    @Operation(summary = "Gets all chats")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all chats",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chats not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok(humanChatMapper.chatToChatDTOList(humanChatService.findAll()));
    }

    @Operation(summary = "Gets chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched human chat with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(humanChatMapper.humanChatToHumanChatDTO(humanChatService.findById(id)));
    }

    @Operation(summary = "Adds a new human chat to game")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully added a new human chat to game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
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
    public ResponseEntity add(@RequestBody HumanChatPostDTO humanChatPostDTO) throws URISyntaxException {
        HumanChat chat = humanChatMapper.HumanChatPostDTOtoHumanChat(humanChatPostDTO);
        humanChatService.add(chat);
        URI uri =  new URI("api/v1/humanChats/" + chat.getId());
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Updates chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated human chat with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody HumanChatPutDTO humanChatPutDTO, @PathVariable Integer id){
        if(id != humanChatPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            humanChatService.update(humanChatMapper.HumanChatPutDTOtoHumanChat(humanChatPutDTO));
            return ResponseEntity.noContent().build();
        }
    }


    @Operation(summary = "Deletes chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted chat with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        humanChatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}