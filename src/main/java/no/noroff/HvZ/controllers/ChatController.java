package no.noroff.HvZ.controllers;

import com.pusher.rest.Pusher;
import com.pusher.rest.data.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.Chatmapper;
import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.dto.chat.ChatDTO;
import no.noroff.HvZ.models.dto.chat.ChatPostDTO;
import no.noroff.HvZ.models.dto.chat.ChatPutDTO;
import no.noroff.HvZ.services.chat.ChatService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/chats")
public class ChatController {
    private ChatService chatService;
    private Chatmapper chatMapper;

    public ChatController(ChatService chatService, Chatmapper chatMapper) {
        this.chatService = chatService;
        this.chatMapper = chatMapper;
    }

    @GetMapping()
    @Operation(summary = "Gets all chats")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all chats",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ChatDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chats Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })

    public ResponseEntity getAll(){

        return ResponseEntity.ok(chatMapper.chatToChatDTOList(chatService.findAll()));
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched chat with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChatDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity findById(@PathVariable Integer id) {
        return  ResponseEntity.ok(chatMapper.chatToChatDTO(chatService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Adds a new chat")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chat added successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChatDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Generic error, unexpected condition was encountered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity add(@RequestBody ChatPostDTO chatPostDTO) throws URISyntaxException {
        Chat chat = chatMapper.ChatPostDTO(chatPostDTO);
        chatService.add(chat);
        URI uri =  new URI("api/v1/chats/" + chat.getId());
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("{id}")
    @Operation(summary = "Update a chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chat updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChatDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity update(@RequestBody ChatPutDTO chatPutDTO, @PathVariable Integer id){
        if(id != chatPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            chatService.update(chatMapper.ChatPutDTO(chatPutDTO));
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Deletes chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chat deleted successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChatDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chat Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        chatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

