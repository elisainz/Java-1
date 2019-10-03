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
@RequestMapping("/api")

public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/games")
    public List<Map<String, Object>> getGames() {
        return gameRepository.findAll()
                .stream()
                .map(Game::getDto)
                .collect(Collectors.toList());
    }


    @RequestMapping("/game_view/{id}")
    public Map<String, Object> getGameView(@PathVariable long id) {
        return gameViewDTO(gamePlayerRepository.getOne(id));
    } //long porque id es numerico


    private Map<String, Object> gameViewDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = gamePlayer.getGame().getDto();
        dto.put("ships", getShipList(gamePlayer.getShips()));
        return dto;
    }

    private List<Map<String, Object>> getShipList(Set<Ship> ships) {
        {
            return ships
                    .stream()
                    .map(Ship::getDto)
                    .collect(Collectors.toList());
        }
    }


}