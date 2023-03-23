package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.dto.chat.ChatDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatDTO;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.player.PlayerDTO;
import no.noroff.HvZ.models.dto.squad.SquadDTO;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatDTO;

import java.util.Set;

@Getter
@Setter
public class GameIdViewDTO {
    private int id;
    private String title;
    private String gameType;
    private String description;
    private String status;
    private String date;
    private int maxPlayers;
    private int playerCount;
    private Set<PlayerDTO> players;
    private Set<SquadDTO> squads;
    private Set<KillDTO> kills;
    private ChatDTO chat;
    private HumanChatDTO humanChat;
    private ZombieChatDTO zombieChat;

}
