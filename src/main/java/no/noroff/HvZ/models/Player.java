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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
