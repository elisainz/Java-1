package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    public Player() {
    }  //constructor vacio

    public Player(String user) {
        this.userName = user;

    }

    public long getId() {
        return id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public String getUserName() {
        return userName;
    }

    public String toString() {
        return userName;
    }

    public Map<String, Object> getPlayerDto() {
        Map<String, Object> dto = new LinkedHashMap<>(); //dto lo pasa a json
        dto.put("id", getId());
        dto.put("email", getUserName());
        return dto;
    }
}