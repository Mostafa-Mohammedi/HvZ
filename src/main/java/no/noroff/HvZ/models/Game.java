package no.noroff.HvZ.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;


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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<Player> player;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Mission> missionList;

    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY)
    private Chat chat;


}
