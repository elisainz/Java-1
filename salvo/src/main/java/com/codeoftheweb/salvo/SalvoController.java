package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping ("/api")

public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping ("/games")
    public List <Map<String,Object>> getGames() {
        return gameRepository.findAll()
                             .stream ()
                             .map(Game->makeGameDTO(Game))
                             .collect(Collectors.toList());
    }
    public Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<>(); //dto lo pasa a json
        dto.put("id", game.getId());
        dto.put("created", game.getGameTime().getTime());
        dto.put("gamePlayers",getGamePlayersList(game.getGamePlayers()));
        return dto;
    }

    public List<Map<String,Object>> getGamePlayersList(Set<GamePlayer> gamePlayers){
        return gamePlayers
          .stream ()
          .map(GamePlayer->makeGamePlayerDTO(GamePlayer))
          .collect(Collectors.toList());
    }

    public Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<>(); //dto lo pasa a json
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayerDTO(gamePlayer.getPlayer()));
        return dto;
    }

    public Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<>(); //dto lo pasa a json
        dto.put("id", player.getId());
        dto.put("email", player.getUserName());
        return dto;
    }


}