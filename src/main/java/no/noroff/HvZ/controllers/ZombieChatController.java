package no.noroff.HvZ.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.ZombieChatMapper;
import no.noroff.HvZ.models.ZombieChat;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatPostDTO;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatPutDTO;
import no.noroff.HvZ.services.zombieChat.ZombieChatService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin("*")
@RestController
@RequestMapping(path="api/v1/zombieChats")
public class ZombieChatController {
    private ZombieChatService zombieChatService;
    private ZombieChatMapper zombieChatMapper;

    public ZombieChatController(ZombieChatService zombieChatService, ZombieChatMapper zombieChatMapper) {
        this.zombieChatService = zombieChatService;
        this.zombieChatMapper = zombieChatMapper;
    }


    @Operation(summary = "Gets all chats for zombies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all zombie chats"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chats not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )

    })
    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok(zombieChatMapper.chatToChatDTOList(zombieChatService.findAll()));
    }

    @Operation(summary = "Gets chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched zombie chat by id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )

    })
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(zombieChatMapper.zombieChatToZombieChatDTO(zombieChatService.findById(id)));
    }

    @Operation(summary = "Adds a new zombie chat")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created new chat"
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
    @PostMapping
    public ResponseEntity add(@RequestBody ZombieChatPostDTO zombieChatPostDTO) throws URISyntaxException {
        ZombieChat chat = zombieChatMapper.ZombieChatPostDTOtoZombieChat(zombieChatPostDTO);
        zombieChatService.add(chat);
        URI uri =  new URI("api/v1/zombieChats/" + chat.getId());
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Updates chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated chat by id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )

    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody ZombieChatPutDTO zombieChatPutDTO, @PathVariable Integer id){
        if(id != zombieChatPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            zombieChatService.update(zombieChatMapper.ZombieChatPutDTOtoZombieChat(zombieChatPutDTO));
            return ResponseEntity.noContent().build();
        }
    }


    @Operation(summary = "Deletes zombie chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted zombie chat by id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )

    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        zombieChatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
