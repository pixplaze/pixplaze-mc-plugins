package com.pixplaze.simpleeventtracker.listener;

import com.google.gson.Gson;
import com.pixplaze.simpleeventtracker.SimpleEventTracker;
import com.pixplaze.simpleeventtracker.json.data.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    private static final Gson gson = new Gson();

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        var profile = getPlayerProfile(player);

        List<PlayerProfile> joinHistory = new ArrayList();
        joinHistory.add(profile);

        // TODO написать десериализацию для листа с профилями и реализовать чтение и запись истории

        writeToFile(gson.toJson(joinHistory));
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

}
