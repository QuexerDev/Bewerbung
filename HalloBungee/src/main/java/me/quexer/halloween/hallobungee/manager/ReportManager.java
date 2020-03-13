package me.quexer.halloween.hallobungee.manager;

import me.quexer.halloween.hallobungee.HalloBungee;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class ReportManager {

    private HalloBungee plugin;

    public ReportManager(HalloBungee plugin) {
        this.plugin = plugin;
    }

    public void report(String targetUUID, String senderUUID, String reason) {
        ProxiedPlayer senderPlayer = plugin.getProxy().getPlayer(UUID.fromString(senderUUID));

    }

}
