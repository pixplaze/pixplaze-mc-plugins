package com.pixplaze.simpleeventtracker;

import com.pixplaze.simpleeventtracker.listener.PlayerListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class SimpleEventTracker extends JavaPlugin {

    private Logger logger = this.getLogger();
    private FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {

        // Генерируем дефолтный конфиг, если его нет в директории плагина
        if (!new File(config.getCurrentPath()).exists()) {
            logger.info("Config isn't exist. Creating...");
            generateConfig();
        }

        registerListeners();

        logger.info("Enabled.");
    }

    @Override
    public void onDisable() {
        logger.info("Disabled.");
    }

    private void generateConfig() {
        config.options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
