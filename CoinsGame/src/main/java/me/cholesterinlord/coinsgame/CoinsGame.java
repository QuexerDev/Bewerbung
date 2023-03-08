package me.cholesterinlord.coinsgame;

import me.cholesterinlord.coinsgame.event.EventManager;
import me.cholesterinlord.coinsgame.session.CalcSession;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class CoinsGame extends JavaPlugin {

    private static CoinsGame INSTANCE;
    private EventManager eventManager;

    public static String PREFIX = "§8[§bCoinsGame§8] §7";

    private HashMap<Player, CalcSession> player_CalcSession;
    private HashMap<Player, Integer> player_Coins;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.eventManager = new EventManager();

        this.player_CalcSession = new HashMap<>();
        this.player_Coins = new HashMap<>();
        // Plugin startup logic

        this.eventManager.onCommand("rechnen", (commandSender, command, s, strings) -> {
            CalcSession calcSession = new CalcSession((Player) commandSender);
            return true;
        });

        this.eventManager.onCommand("coins", (commandSender, command, s, strings) -> {
            Player player = (Player) commandSender;
            if(!this.player_Coins.containsKey(player))
                this.player_Coins.put(player, 0);
            player.sendMessage(PREFIX + "Du hast §e"+this.player_Coins.get(player) + " §7Coins");
            return true;
        });

        this.eventManager.onCommand("stopRechnen", (commandSender, command, s, strings) -> {
            Player player = (Player) commandSender;
            if(this.player_CalcSession.containsKey(player))
                this.player_CalcSession.get(player).end();
            return true;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CoinsGame getInstance() {
        return INSTANCE;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public HashMap<Player, CalcSession> getPlayer_CalcSession() {
        return player_CalcSession;
    }

    public HashMap<Player, Integer> getPlayer_Coins() {
        return player_Coins;
    }
}
