package no.noroff.HvZ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private int gameRef;

    @JsonIgnore
    @OneToMany(mappedBy = "squad")
    private Set<Player> players;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne
    @JoinColumn(name = "squadMember")
    private SquadMember squadMember;


}
