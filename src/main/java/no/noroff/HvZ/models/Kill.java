package no.noroff.HvZ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String time_of_death;
    @Column(length = 50, nullable = false)
    private String story;
    private double lat;
    private double lng;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @OneToOne
    private Player player;

}
