package com.pixplaze.simpleeventtracker.listener;

import com.pixplaze.simpleeventtracker.SimpleEventTracker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage("Добро пожаловать на сервер Шизофрения");
        e.getPlayer().sendMessage("SimpleEventTracker");
        var profile = getPlayerProfile(player);
        writeToFile(profile.toString());
    }

    static private PlayerProfile getPlayerProfile(Player player) {
        return new PlayerProfile(player.getUniqueId(), player.getName());
    }

    private static void writeToFile(String json) {
        var file = new File(SimpleEventTracker.getInstance().getDataFolder() + "/userprofile.json");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class PlayerProfile {
        final UUID uuid;
        final String name;

        public PlayerProfile(UUID uuid, String name) {
            this.uuid = uuid;
            this.name = name;
        }

        @Override
        public String toString() {
            return "{" +
                    "uuid: " + uuid +
                    ", name: '" + name + '\'' +
                    '}';
        }
    }

}
