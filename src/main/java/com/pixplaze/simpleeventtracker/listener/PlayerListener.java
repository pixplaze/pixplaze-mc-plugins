package com.pixplaze.simpleeventtracker.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage("Добро пожаловать на сервер Шизофрения");
        e.getPlayer().sendMessage("SimpleEventTracker");
    }

}
