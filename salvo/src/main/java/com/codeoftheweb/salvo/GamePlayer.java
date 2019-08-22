package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")//agregar columna con este nombre
    private Game game;

    public GamePlayer() {
    }


    public GamePlayer(Date joinDate) {
        this.joinDate = joinDate;
    }

    public long getId() {
        return id;
    }

    public Date getJoinDate() {
        return joinDate;
    }
}




