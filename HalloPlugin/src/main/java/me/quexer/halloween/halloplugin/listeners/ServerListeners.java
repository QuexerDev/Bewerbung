package me.quexer.halloween.halloplugin.listeners;

import de.dytanic.cloudnet.bridge.event.bukkit.BukkitSubChannelMessageEvent;
import de.dytanic.cloudnet.lib.utility.document.Document;
import me.quexer.api.quexerapi.event.EventManager;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.Bukkit;

import java.util.UUID;

public class ServerListeners {

    private HalloPlugin plugin;

    public ServerListeners(HalloPlugin plugin) {
        this.plugin = plugin;
        initListeners();
    }

    private void initListeners() {
        plugin.getQuexerAPI().getEventManager().registerEvent(BukkitSubChannelMessageEvent.class, (EventManager.EventListener<BukkitSubChannelMessageEvent>) event -> {
            System.out.println(event.getDocument().convertToJsonString());

            if (event.getChannel().equalsIgnoreCase("ban")) {
                if (event.getMessage().equalsIgnoreCase("check")) {
                    plugin.getInventoryManager().checkInv(Bukkit.getPlayer(UUID.fromString(event.getDocument().getString("uuid"))), event.getDocument().getString("target"), event.getDocument());
                }
            }
        });



    }
}
