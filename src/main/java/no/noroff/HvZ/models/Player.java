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
    private String lastCheckInTime;
    private String username;
    private double lat;
    private double lng;
    private boolean isPatientZero;
    @Column(length = 50, nullable = false)
    private String biteCode;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "player")
    private Set<Chat> chat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;
    @OneToOne(mappedBy = "player")
    private SquadMember squadMember;

    @OneToOne(mappedBy = "player")
    private Kill kill;
}
