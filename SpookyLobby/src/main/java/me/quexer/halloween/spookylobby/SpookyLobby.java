package me.quexer.halloween.spookylobby;


import me.quexer.api.quexerapi.api.LocationAPI;
import me.quexer.api.quexerapi.misc.Document;
import me.quexer.halloween.halloplugin.HalloPlugin;
import me.quexer.halloween.spookylobby.commands.SetLocCMD;
import me.quexer.halloween.spookylobby.listeners.PlayerListeners;
import me.quexer.halloween.spookylobby.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class SpookyLobby extends HalloPlugin {

    private static SpookyLobby instance;

    private LocationManager locationManager;
    private HotbarManager hotbarManager;
    private CprManager cprManager;
    private DailyRewardManager dailyRewardManager;
    private CaseManager caseManager;
    private GadgetManager gadgetManager;
    private InventoryManager inventoryManager;
    private ScoreboardManager scoreboardManager;

    @Override
    public void init() {

        setTabOnThisServer(true);
        setNickOnThisServer(false);

        new PlayerListeners(this);
        new SetLocCMD(this);

        Bukkit.getWorlds().forEach(world -> {
            System.out.println(world.getName());
            world.getEntities().forEach(entity -> {
                entity.remove();
            });
        });

        locationManager = new LocationManager(this);
        hotbarManager = new HotbarManager(this);
        cprManager = new CprManager(this);
        dailyRewardManager = new DailyRewardManager(this);
        caseManager = new CaseManager(this);
        gadgetManager = new GadgetManager(this);
        inventoryManager = new InventoryManager(this);
        scoreboardManager = new ScoreboardManager(this);



    }

    @Override
    public void disable() {

    }

    @Override
    public String getPrefix() {
        return "§cLobby §8➜ ";
    }

    public static SpookyLobby getInstance() {
        return instance;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public HotbarManager getHotbarManager() {
        return hotbarManager;
    }

    public CprManager getCprManager() {
        return cprManager;
    }

    public DailyRewardManager getDailyRewardManager() {
        return dailyRewardManager;
    }

    public CaseManager getCaseManager() {
        return caseManager;
    }

    public GadgetManager getGadgetManager() {
        return gadgetManager;
    }

    public InventoryManager getInventoryManagerLobby() {
        return inventoryManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
