package no.noroff.HvZ.models.dto.mission;


import lombok.Data;

@Data
public class MissionPostDTO {
    private String name;
    private boolean is_human_visible;
    private boolean is_zombie_visible;
    private  String description;
    private String start_time;
    private String end_time;
}
