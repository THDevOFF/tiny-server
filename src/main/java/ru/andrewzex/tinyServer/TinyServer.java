package ru.andrewzex.tinyServer;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class TinyServer extends JavaPlugin {

    private SimpleHttpServer server;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        int port = getConfig().getInt("port", 25984);
        String ip = getConfig().getString("ip", "0.0.0.0");

        try {
            server = new SimpleHttpServer(getDataFolder(), ip, port);
            server.start();
            getLogger().info("Веб-сервер запущен на " + ip + ":" + port);
        } catch (IOException e) {
            getLogger().severe("Не удалось запустить веб-сервер: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        if (server != null) {
            server.stop();
            getLogger().info("Веб-сервер остановлен.");
        }
    }
}
