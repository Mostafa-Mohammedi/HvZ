package no.noroff.HvZ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.*;


import java.util.Set;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String gameType;
    @Column(nullable = false)
    private String description;

    //In progress, completed, registration
    @Column(nullable = false)
    private String status;
    private Date date;


    @Column(nullable = false)
    private int maxPlayers;

    @Column(nullable = false)
    private int playerCount;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<Player> players;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Mission> missionList;
    
    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY)
    private Chat chat;


    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<Squad> squads;

}
