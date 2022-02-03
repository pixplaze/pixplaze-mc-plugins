package com.pixplaze.simpleeventtracker.listener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixplaze.simpleeventtracker.SimpleEventTracker;
import com.pixplaze.simpleeventtracker.json.data.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    private static final Gson gson = new Gson();

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        var profile = getPlayerProfile(player);

        String joinHistoryData = readFromFile(SimpleEventTracker.getInstance().getDataFolder() +
                "/userprofile.json");

        /* Десериализуем прочитанный файл и добавляем к нему новую запись */
        Type playerProfileList = new TypeToken<ArrayList<PlayerProfile>>(){}.getType();
        List<PlayerProfile> joinHistory = gson.fromJson(joinHistoryData, playerProfileList);
        joinHistory.add(profile);
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

    private static String readFromFile(String path) {
        String data = "";

        try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(path),
                StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                data += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
