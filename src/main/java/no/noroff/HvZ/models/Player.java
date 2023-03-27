package no.noroff.HvZ.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Entity
@Getter
@Setter
/**
 * Player represents the player in the HvZ game
 */
public class Player {

    /**
     * the user id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * boolean id to check if user is human or zombie
     */
    private boolean isHuman;

    private String lastCheckInTime;

    /**
     * player username get from the user table
     */
    private String username;
    private double lat;
    private double lng;
    private boolean isPatientZero;
    /**
     * user token from the user table get from keycloak
     */
    private String userTokenRef;

    /**
     * game id from the game entiti (id)
     */
    int gameRef;

    /**
     * Squad id get from the squad
     */
    private int squadRef;

    @Column(length = 50, nullable = false)
    private String biteCode;


    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;
    @OneToOne(mappedBy = "player")
    private SquadMember squadMember;


    @OneToMany(mappedBy = "player")
    private Set<Kill> kills;



}
