package no.noroff.HvZ.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SquadMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rank;

    @OneToOne()
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne()
    @JoinColumn(name = "squad_id")
    private Squad squad;
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
