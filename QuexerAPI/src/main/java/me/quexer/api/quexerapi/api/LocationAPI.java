package me.quexer.api.quexerapi.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.quexer.api.quexerapi.QuexerAPI;
import me.quexer.api.quexerapi.misc.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface LocationAPI {

    public void setLocation(String name, Location loc);

    public boolean exist(String name);

    public Location getLocation(String name);
}
