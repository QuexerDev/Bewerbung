package me.quexer.halloween.halloplugin.commands;

import me.quexer.api.quexerapi.api.NickAPI;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

public class NickCMD {

    private HalloPlugin plugin;

    public NickCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("nick", (sender, command, s, args) -> {
            Player player = (Player) sender;
            System.out.println("nick");
            if(plugin.getGroup(player).hasPermission(3)) {
                if(plugin.isNickOnThisServer()) {
                    if (NickAPI.hasNick(player) == false) {
                        plugin.getBackendManager().nick(player);
                    } else {
                        plugin.getBackendManager().unnick(player);
                    }
                } else {
                    player.sendMessage(plugin.getError()+"§cDas Nicken ist hier verboten!");
                }
            }
            return true;
        });

    }

}
