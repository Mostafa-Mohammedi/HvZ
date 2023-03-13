package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.GameMapper;
import no.noroff.HvZ.mappers.PlayerMapper;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.dto.game.GameDTO;
import no.noroff.HvZ.models.dto.game.GamePostDTO;
import no.noroff.HvZ.models.dto.game.GamePutDTO;
import no.noroff.HvZ.services.game.GameService;
import no.noroff.HvZ.services.player.PlayerService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/games")
public class GameController {


    private final GameService gameService;
    private final GameMapper gameMapper;
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    public GameController(GameService gameService, GameMapper gameMapper, PlayerMapper playerMapper, PlayerService playerService) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @Operation(summary = "Gets game by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
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
        return ResponseEntity.ok(gameMapper.gameToGameDTO(gameService.findById(id)));
    }

    @Operation(summary = "Gets all games")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = GameDTO.class)))
                    }
            )
    })

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(gameMapper.gameToGameDTO(gameService.findAll()));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody GamePostDTO entity) throws URISyntaxException {
        Game game = gameMapper.gamePostDTOtoGame(entity);
        gameService.add(game);
        URI uri =  new URI("api/v1/games/" + game.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody GamePutDTO entity, @PathVariable Integer id) {
        if (id != entity.getId())
            return ResponseEntity.badRequest().build();
        gameService.update(
                gameMapper.gamePutDTOtoGame(entity)
        );
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{id}/players")
    public ResponseEntity getPlayers(@PathVariable Integer id){
        return ResponseEntity.ok(playerMapper.playerToPlayerDTO(gameService.getPlayers(id)));
    }

    @PutMapping("{id}/players")
    public ResponseEntity updatePlayers(@PathVariable Integer id, @RequestBody int[] playerIds){
        gameService.updatePlayers(id, playerIds);
        return ResponseEntity.noContent().build();
    }


}
