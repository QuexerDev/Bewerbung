package me.quexer.halloween.halloplugin.api;

import me.quexer.api.quexerapi.QuexerAPI;
import me.quexer.api.quexerapi.api.LocationAPI;
import me.quexer.api.quexerapi.misc.Document;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;

public class LocationAPIImpl implements LocationAPI {

    private HalloPlugin plugin;

    public LocationAPIImpl(HalloPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void setLocation(String name, Location loc) {

        Document location = new Document()
                .append("X", Double.valueOf(loc.getX()))
                .append("Y", Double.valueOf(loc.getY()))
                .append("Z", Double.valueOf(loc.getZ()))
                .append("Yaw", Float.valueOf(loc.getYaw()))
                .append("Pitch", Float.valueOf(loc.getPitch()))
                .append("World", loc.getWorld().getName());
        plugin.getLocationsDocument().append(name, location);

    }

    @Override
    public boolean exist(String name) {
        if (plugin.getLocationsDocument().contains(name) == false) {
            return false;
        }
        return true;
    }

    @Override
    public Location getLocation(String name) {
        Document location = plugin.getLocationsDocument().getDocument(name);
        double x = location.getDouble("X");
        double y = location.getDouble("Y");
        double z = location.getDouble("Z");
        double yaw = location.getDouble("Yaw");
        double pitch = location.getDouble("Pitch");
        World w = Bukkit.getWorld(location.getString("World"));
        Location loc = new Location(w, x, y, z);
        loc.setYaw((float)yaw);
        loc.setPitch((float)pitch);
        return loc;
    }
}
