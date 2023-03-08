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
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isHuman;

    private boolean isPatientZero;

    private String biteCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "player")
    private Set<Chat> chat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;
}
