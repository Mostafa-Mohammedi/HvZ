package no.noroff.HvZ.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int missionId;
    private String name;
    private boolean is_human_visible;
    private boolean is_zombie_visible;
    private  String description;
    private String start_time;
    private String end_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
}
