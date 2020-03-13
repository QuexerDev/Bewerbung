package me.quexer.halloween.halloplugin.commands;

import me.quexer.api.quexerapi.misc.uuid.UUIDFetcher;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

public class BanCMD {

    private HalloPlugin plugin;

    public BanCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("ban", (sender, command, s, args) -> {
            Player player = (Player) sender;
            Group group = plugin.getGroup(player);
            if(group.hasPermission(9)) {
                if(args.length == 1) {
                    plugin.getInventoryManager().banInv(args[0], player);
                } else {
                    player.sendMessage(plugin.getError()+"§7Benutze§8: §c/ban <Spieler>");
                }
            }
            return true;
        });

    }

}
