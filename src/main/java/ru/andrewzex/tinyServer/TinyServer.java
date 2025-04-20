package ru.andrewzex.tinyServer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class TinyServer extends JavaPlugin {

    private FileConfiguration lang;
    private File langFile;
    private SimpleHttpServer server;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadLang();
        startServer();
        getLogger().info("TinyServer enabled.");
    }

    @Override
    public void onDisable() {
        stopServer();
        getLogger().info("TinyServer disabled.");
    }

    private void loadLang() {
        String langCode = getConfig().getString("language", "en");
        File langDir = new File(getDataFolder(), "lang");
        if (!langDir.exists()) langDir.mkdirs();

        File langFile = new File(langDir, langCode + ".yml");
        if (!langFile.exists()) {
            saveResource("lang/" + langCode + ".yml", false);
        }

        lang = YamlConfiguration.loadConfiguration(langFile);
    }

    private void reloadEverything() {
        reloadConfig();
        loadLang();
        stopServer();
        startServer();
    }

    private void startServer() {
        String ip = getConfig().getString("ip", "0.0.0.0");
        int port = getConfig().getInt("port", 25984);
        try {
            server = new SimpleHttpServer(getDataFolder(), ip, port);
            server.start();
        } catch (IOException e) {
            getLogger().severe("Failed to start web server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void stopServer() {
        if (server != null) server.stop();
    }

    private String msg(String key) {
        return lang.getString("prefix", "[TinyServer] ") + lang.getString(key, key);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("tinyserver.admin")) {
            sender.sendMessage(msg("no_permission"));
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§e/tinyserver reload §7- Reload config and language");
            sender.sendMessage("§e/tinyserver on §7- Start web server");
            sender.sendMessage("§e/tinyserver off §7- Stop web server");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                reloadEverything();
                sender.sendMessage(msg("reloaded"));
                break;
            case "on":
                stopServer();
                startServer();
                sender.sendMessage(msg("server_on"));
                break;
            case "off":
                stopServer();
                sender.sendMessage(msg("server_off"));
                break;
            default:
                sender.sendMessage("§cUnknown subcommand.");
                break;
        }
        return true;
    }
}
