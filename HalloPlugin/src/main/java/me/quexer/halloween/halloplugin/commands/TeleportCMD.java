package me.quexer.halloween.halloplugin.commands;

import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCMD {

    private HalloPlugin plugin;

    public TeleportCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("tp", (sender, command, s, args) -> {
            Player player = (Player) sender;
            if(plugin.getGroup(player).hasPermission(12)) {
                Player t;
                if (args.length == 1) {
                    t = Bukkit.getPlayer(args[0]);
                    if (t == null) {
                        player.sendMessage(plugin.getPrefix() + "§cDieser Spieler ist nicht Online!");
                        return true;
                    }

                    player.teleport(t);
                    player.sendMessage(plugin.getPrefix() + "§7Du hast dich zu " + t.getDisplayName() + " §7teleportiert");
                } else if (args.length == 2) {
                    t = Bukkit.getPlayer(args[0]);
                    Player e = Bukkit.getPlayer(args[1]);
                    if (t == null || e == null) {
                        player.sendMessage(plugin.getPrefix() + "§cDieser Spieler ist nicht Online!");
                        return true;
                    }

                    t.teleport(e);
                    player.sendMessage(plugin.getPrefix() + "§7Du hast " + t.getDisplayName() + " §7zu " + e.getDisplayName() + " §7teleportiert");
                } else
                    player.sendMessage(plugin.getPrefix() + "§7Benutze§8: §c/Tp <Spieler> <Spieler>");

            } else
                player.sendMessage(plugin.getPrefix() + "§cDazu hast du keine Rechte!");
            return true;
        });

    }


    
}
