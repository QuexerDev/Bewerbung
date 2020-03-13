package me.quexer.halloween.halloplugin.callbacks;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.utility.document.Document;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class CheckPlayerCallback implements Consumer<Player> {

    private HalloPlugin plugin;

    private String targetUUID;
    private String senderUUID;
    private Player sender;

    public CheckPlayerCallback(HalloPlugin plugin, String targetUUID, String senderUUID, Player sender) {
        this.plugin = plugin;
        this.targetUUID = targetUUID;
        this.senderUUID = senderUUID;
        this.sender = sender;
    }

    @Override
    public void accept(Player player) {

        CloudAPI.getInstance().sendCustomSubProxyMessage("ban", "check", new Document("uuid", senderUUID).append("target", targetUUID), "Bungee-1");
        player.sendTitle("§eEinen Moment...", "§7Die Daten werden geladen§8!");
    }




}
