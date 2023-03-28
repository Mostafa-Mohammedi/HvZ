package no.noroff.HvZ.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.HvZ.mappers.GameMapper;
import no.noroff.HvZ.mappers.KillMapper;
import no.noroff.HvZ.mappers.PlayerMapper;
import no.noroff.HvZ.mappers.SquadMapper;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.dto.game.GameDTO;
import no.noroff.HvZ.models.dto.game.GamePostDTO;
import no.noroff.HvZ.models.dto.game.GamePutDTO;
import no.noroff.HvZ.services.game.GameService;
import no.noroff.HvZ.services.kill.KillService;
import no.noroff.HvZ.services.player.PlayerService;
import no.noroff.HvZ.services.squad.SquadService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final KillMapper killMapper;
    private final KillService killService;
    private final SquadMapper squadMapper;
    private final SquadService squadService;

    public GameController(GameService gameService, GameMapper gameMapper, PlayerMapper playerMapper, PlayerService playerService, KillMapper killMapper, KillService killService, SquadMapper squadMapper, SquadService squadService) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
        this.playerMapper = playerMapper;
        this.playerService = playerService;
        this.killMapper = killMapper;
        this.killService = killService;
        this.squadMapper = squadMapper;
        this.squadService = squadService;
    }

    @Operation(summary = "Gets game by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched game with given ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Game not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(gameMapper.gameToGameIdViewDTO(gameService.findById(id)));
    }

    @Operation(summary = "Gets all games")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all games",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = GameDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Game not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(gameMapper.gameToGameDTO(gameService.findAll()));
    }


    @Operation(summary = "Adds a new game")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Game added successfully",
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
    public ResponseEntity add(@RequestBody GamePostDTO entity) throws URISyntaxException {
        Game game = gameMapper.gamePostDTOtoGame(entity);
        gameService.add(game);
        URI uri =  new URI("api/v1/games/" + game.getId());
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Updates game by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Game updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Game not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody GamePutDTO entity, @PathVariable Integer id) {
        if (id != entity.getId())
            return ResponseEntity.badRequest().build();
        gameService.update(
                gameMapper.gamePutDTOtoGame(entity)
        );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Gets all players in game by game_id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all players in game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Players Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping("{id}/players")
    public ResponseEntity getPlayers(@PathVariable Integer id){
        return ResponseEntity.ok(playerMapper.playerToPlayerDTO(gameService.getPlayers(id)));
    }

    @Operation(summary = "Updates players in game by game_id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated all players in game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Players Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}/players")
    public ResponseEntity updatePlayers(@PathVariable Integer id, @RequestBody int[] playerIds){
        gameService.updatePlayers(id, playerIds);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Gets all kills in game by game_id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all kills in game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Kills Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping("{id}/kills")
    public ResponseEntity getKills(@PathVariable Integer id){
        return ResponseEntity.ok(killMapper.killMappedToKillDTO(gameService.getKills(id)));
    }


    @Operation(summary = "Updates kills in game by game_id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated all kills in game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Kills Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}/kills")
    public ResponseEntity updateKills(@PathVariable Integer id, @RequestBody int[] killIds){
        gameService.updateKills(id, killIds);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Gets all squads in game by game_id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all squads in game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Squads Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping("{id}/squads")
    public ResponseEntity getSquads(@PathVariable Integer id){
        return ResponseEntity.ok(squadMapper.squadToSquadDTO(gameService.getSquads(id)));
    }


    @Operation(summary = "Updates squads in game by game_id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated all squads in game",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Squads Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping("{id}/squads")
    public ResponseEntity updateSquads(@PathVariable Integer id, @RequestBody int[] squadIds){
        gameService.updateSquads(id, squadIds);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes game by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully deleted game with given ID"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Game not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })

    @DeleteMapping("{id}/game")
    public ResponseEntity deleteGame(@PathVariable Integer id){
       gameService.deleteById(id);
       return  ResponseEntity.noContent().build();
    }
}
