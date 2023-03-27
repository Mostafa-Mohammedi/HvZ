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

    private String date;

    private double lat;
    private double lng;
    private String map;

    @Column(nullable = false)
    private int maxPlayers;

    @Column(nullable = false)
    private int playerCount;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<Player> players;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Mission> missionList;

    @JsonIgnore
    @OneToOne(mappedBy = "game")
    private Chat chat;

    @JsonIgnore
    @OneToOne(mappedBy = "game")
    private ZombieChat zombieChat;
    @JsonIgnore
    @OneToOne(mappedBy = "game")
    private HumanChat humanChat;


    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY)
    private SquadMember squadMember;


    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<Squad> squads;

    @OneToMany(mappedBy = "game")
    private Set<Kill> kills;



}