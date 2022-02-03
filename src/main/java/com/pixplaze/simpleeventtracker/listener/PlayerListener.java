package com.pixplaze.simpleeventtracker.listener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixplaze.simpleeventtracker.SimpleEventTracker;
import com.pixplaze.simpleeventtracker.json.data.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerListener implements Listener {

    private static final Logger logger = SimpleEventTracker.getInstance().getLogger();
    private static final Gson gson = new Gson();
    private static final String filepath = SimpleEventTracker.getInstance().getDataFolder() + "/userprofile.json";

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        var profile = getPlayerProfile(e.getPlayer());
        saveProfile(profile);
    }

    private static PlayerProfile getPlayerProfile(Player player) {
        return new PlayerProfile(player.getUniqueId(), player.getName());
    }

    private static void saveProfile(PlayerProfile profile) {
        var file = new File(filepath);
        List<PlayerProfile> joinHistory = new ArrayList<>();

        try {
            if (file.createNewFile()) {
                logger.info("userprofile.json doesn't exist. Creating...");
            } else {
                var joinHistoryData = readFromFile(file);
                /* Десериализуем прочитанный файл */
                Type playerProfileList = new TypeToken<ArrayList<PlayerProfile>>(){}.getType();
                joinHistory = gson.fromJson(joinHistoryData, playerProfileList);
            }
            joinHistory.add(profile);
            writeToFile(file, gson.toJson(joinHistory));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(File file, String json) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(File file) {
        String data = "";

        try (var br = new BufferedReader(new FileReader(file))) {
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
