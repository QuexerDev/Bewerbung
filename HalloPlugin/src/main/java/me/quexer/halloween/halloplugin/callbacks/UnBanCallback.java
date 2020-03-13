package me.quexer.halloween.halloplugin.callbacks;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.utility.document.Document;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class UnBanCallback implements Consumer<Player> {

    private HalloPlugin plugin;
    private String targetUUID;
    private String senderUUID;
    private String type;

    public UnBanCallback(HalloPlugin plugin, String targetUUID, String senderUUID, String type) {
        this.plugin = plugin;
        this.targetUUID = targetUUID;
        this.senderUUID = senderUUID;
        this.type = type;
    }

    @Override
    public void accept(Player player) {
        player.closeInventory();
        switch (type) {
            case "BAN":
                CloudAPI.getInstance().sendCustomSubProxyMessage("ban", "unban", new Document("uuid", senderUUID).append("reason", "Teaming").append("target", targetUUID), "Bungee-1");
                break;
            case "MUTE":
                CloudAPI.getInstance().sendCustomSubProxyMessage("ban", "unmute", new Document("uuid", senderUUID).append("reason", "Teaming").append("target", targetUUID), "Bungee-1");
                break;
        }
    }
}
