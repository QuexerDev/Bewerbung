package me.quexer.halloween.halloplugin.commands;

import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

public class UnBanCMD {

    private HalloPlugin plugin;

    public UnBanCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("unban", (sender, command, s, args) -> {
            Player player = (Player) sender;
            Group group = plugin.getGroup(player);
            if(group.hasPermission(9)) {
                if(args.length == 1) {
                    plugin.getInventoryManager().unBanInv(args[0], player);
                } else {
                    player.sendMessage(plugin.getError()+"§7Benutze§8: §c/unban <Spieler>");
                }
            }
            return true;
        });

    }

}
