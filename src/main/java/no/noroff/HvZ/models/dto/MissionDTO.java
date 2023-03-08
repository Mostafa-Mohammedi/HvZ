package no.noroff.HvZ.models.dto;

import lombok.Data;

@Data
public class MissionDTO {

    private int missionId;
    private String name;
    private boolean is_human_visible;
    private boolean is_zombie_visible;
    private  String description;
    private String start_time;
    private String end_time;

}
