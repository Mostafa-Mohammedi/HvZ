package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.Chatmapper;
import no.noroff.HvZ.models.dto.ChatDTO;
import no.noroff.HvZ.services.chat.ChatService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/Chat")
public class ChatController {

    private ChatService chatService;
    private Chatmapper chatMapper;

    public ChatController(ChatService chatService, Chatmapper chatMapper) {
        this.chatService = chatService;
        this.chatMapper = chatMapper;
    }

    @GetMapping()
    @Operation(summary = "Get all chat")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChatDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })

    public ResponseEntity getAll(){
        return  ResponseEntity.ok(chatMapper.chatToChatDTOList(chatService.findAll()));
    }




    @GetMapping("{id}")
    @Operation(summary = "Gets Chat by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChatDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity findById(@PathVariable Integer id) {
        return  ResponseEntity.ok(chatMapper.chatToChatDTO(chatService.findById(id)));
    }
}
