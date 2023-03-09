package no.noroff.HvZ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    private String description;



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
