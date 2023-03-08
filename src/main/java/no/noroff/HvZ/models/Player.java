package no.noroff.HvZ.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;

    private boolean isHuman;

    private boolean isPatientZero;

    private String biteCode;

   /*
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

}
