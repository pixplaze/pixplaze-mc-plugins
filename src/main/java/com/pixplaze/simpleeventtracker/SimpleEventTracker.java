package com.pixplaze.simpleeventtracker;

import com.pixplaze.simpleeventtracker.listener.PlayerListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class SimpleEventTracker extends JavaPlugin {

    private static SimpleEventTracker instance;

    private Logger logger = this.getLogger();
    private FileConfiguration config = this.getConfig();

    public SimpleEventTracker() {
        instance = this;
    }

    public static SimpleEventTracker getInstance() {
        if (instance == null) {
            instance = new SimpleEventTracker();
        }
        return instance;
    }

    @Override
    public void onEnable() {

        /* Генерируем дефолтный конфиг, если его нет в директории плагина */
        if (!new File(config.getCurrentPath()).exists()) {
            logger.info("Config file isn't exist. Creating...");
            generateConfig();
        }

        /* Регистрируем все обработчики событий, используемые плагином */
        registerListeners();

        /* Выводим сообщение о том, что плагин успешно запущен */
        logger.info("Enabled.");
    }

    @Override
    public void onDisable() {
        /* Выводим сообщение о том, что работа плагина завершена */
        logger.info("Disabled.");
    }

    private void generateConfig() {
        /* Копируем содержимое из config.yml в ресурсах пакета и записываем в файл в дефолтной директории */
        config.options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
