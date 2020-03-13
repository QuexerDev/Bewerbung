package me.quexer.halloween.halloplugin.commands;

import me.quexer.api.quexerapi.models.Group;
import me.quexer.api.quexerapi.models.GroupPlayer;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCMD {

    private HalloPlugin plugin;


    public HelpCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("help", (sender, command, s, strings) -> {

            Player player = (Player) sender;
            Group group = plugin.getGroup(player);
            sender.sendMessage(plugin.getPrefix() + "§8▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
            sender.sendMessage(plugin.getPrefix() + "§e/help §8- §bSiehe alle Commands");
            sender.sendMessage(plugin.getPrefix() + "§e/friend §8- §bVerwalte deine Freunde");
            sender.sendMessage(plugin.getPrefix() + "§e/report §8- §bMelde einen Spieler");
            sender.sendMessage(plugin.getPrefix() + "§e/msg §8- §bSende einem Freund eine private Nachricht");
            sender.sendMessage(plugin.getPrefix() + "§e/coins §8- §bSehe deine Coins, Keys und CPR");
            if (group.hasPermission(3)) {
                sender.sendMessage(plugin.getPrefix() + "§e/nick §8- §bLass Niemanden dich erkennen");
            }
            if (group.hasPermission(5)) {
                sender.sendMessage(plugin.getPrefix() + "§e/check §8- §bErhalte die Daten eines Spielers");
            }
            if (group.hasPermission(8)) {
                sender.sendMessage(plugin.getPrefix() + "§e/ban §8- §bBestrafe Spieler für Regelvertöße");
                sender.sendMessage(plugin.getPrefix() + "§e/reasons §8- §bErhalte eine Liste mit Gründen");
                sender.sendMessage(plugin.getPrefix() + "§e/unban §8- §bBefreie einen Spieler von einem Ban");
                sender.sendMessage(plugin.getPrefix() + "§e/unmute §8- §bBefreie einen Spieler von einem Mute");
            }
            if (group.hasPermission(11)) {
                sender.sendMessage(plugin.getPrefix() + "§e/group §8- §bVerwalte über den Rang eines Spielers");
            }
            sender.sendMessage(plugin.getPrefix());
            sender.sendMessage(plugin.getPrefix() + "§eCPR §8= §6C§eoins§6P§eer§6R§eound");
            sender.sendMessage(plugin.getPrefix() + "§8▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
            return true;
        });

    }
    

}
