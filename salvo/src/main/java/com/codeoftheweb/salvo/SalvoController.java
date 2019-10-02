package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
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
    private GamePlayerRepository gamePlayerRepository;

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

    @RequestMapping("/game_view/{id}")
    public Map<String, Object> getGameView (@PathVariable long id) {
        return gameViewDTO(gamePlayerRepository.findById(id).get());
    } //long porque id es numerico


    private Map<String, Object> gameViewDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<>();

        dto.put("id", gamePlayer.getGame().getId());
        dto.put("creationDate", gamePlayer.getGame().getGameTime());
        dto.put("gamePlayers", getGamePlayersList(gamePlayer.getGame().getGamePlayers()));
        dto.put("ships", getShipList(gamePlayer.getShips()));

        return dto;
    }
    private List<Map<String, Object>> getShipList(Set<Ship> ships) {
        { return ships
                .stream()
                .map(ship -> makeshipDTO(ship))
                .collect(Collectors.toList());
        }
    }
    private Map<String,Object> makeshipDTO(Ship ship){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("shipType", ship.getType());
        dto.put("shipLocations", ship.getShipLocations());
        return dto;
    }

}