package no.noroff.HvZ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
/**
 * Kill  class represents a player kill in the HvZ game
 */
public class Kill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String time_of_death;
    @Column(length = 50, nullable = false)
    private String story;
    private double lat;
    private double lng;
    /**
     * player id
     */
    private int playerRef;
    /**
     * game id
     */
    private int gameRef;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

}
