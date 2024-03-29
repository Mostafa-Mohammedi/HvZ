package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.HumanChat;
import no.noroff.HvZ.models.dto.chat.ChatDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatDTO;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatDTO;

import java.util.Date;


import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GameDTO {
    private int id;
    private String title;
    private String gameType;
    private String description;
    private String status;
    private String date;
    private double lat;
    private double lng;
    private String map;
    private int maxPlayers;
    private int playerCount;
    private Set<Integer> players;
    private Set<Integer> squads;
    private Set<Integer> kills;
    private ChatDTO chat;
    private HumanChatDTO humanChat;
    private ZombieChatDTO zombieChat;
}
