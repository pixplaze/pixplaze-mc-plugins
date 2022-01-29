package com.pixplaze.simpleeventtracker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SimpleEventTracker extends JavaPlugin {

    private Logger logger = this.getLogger();

    @Override
    public void onEnable() {


        logger.info("Enabled.");
    }

    @Override
    public void onDisable() {
        logger.info("Disabled.");
    }

    private void registerEvents() {
        Reflections
    }
}
