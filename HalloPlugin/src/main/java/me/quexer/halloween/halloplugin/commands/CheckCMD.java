package me.quexer.halloween.halloplugin.commands;

import me.quexer.api.quexerapi.misc.uuid.UUIDFetcher;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.halloplugin.HalloPlugin;
import me.quexer.halloween.halloplugin.callbacks.CheckPlayerCallback;
import org.bukkit.entity.Player;

public class CheckCMD {

    private HalloPlugin plugin;

    public CheckCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("check", (sender, command, s, args) -> {

            Player player = (Player) sender;
            Group group = plugin.getGroup(player);
            if(group.hasPermission(9)) {
                if (args.length == 1) {
                    UUIDFetcher.getUUID(args[0], targetUUID -> {
                        if (targetUUID == null) {
                            sender.sendMessage(plugin.getPrefix() + "§cDieser Spieler existiert nicht!");
                            return;
                        }

                        new CheckPlayerCallback(plugin, targetUUID.toString(), ((Player) sender).getUniqueId().toString(), (Player) sender).accept((Player) sender);
                    });
                } else {
                    player.sendMessage(plugin.getError()+"§7Benutze§8: §c/check <Spieler>");
                }
            }
            return true;
        });

    }

}
