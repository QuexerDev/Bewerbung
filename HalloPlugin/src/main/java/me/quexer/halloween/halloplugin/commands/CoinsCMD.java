package me.quexer.halloween.halloplugin.commands;

import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class CoinsCMD {

    private HalloPlugin plugin;


    public CoinsCMD(HalloPlugin plugin) {
        this.plugin = plugin;

        plugin.onCommand("coins", (sender, command, s, args) -> {
            sender.sendMessage("");
            sender.sendMessage(String.format(plugin.getPrefix() + "§7Du hast §e" + new DecimalFormat("###,###,###,###,###,###").format(plugin.getData(((Player) sender).getUniqueId().toString()).getCoins()) + " §7Coin(s)§8."));
            sender.sendMessage(String.format(plugin.getPrefix() + "§7Du hast §e" + new DecimalFormat("###,###,###,###,###,###").format(plugin.getData(((Player) sender).getUniqueId().toString()).getKeys()) + " §7Key(s)§8."));
            sender.sendMessage(String.format(plugin.getPrefix() + "§7Du hast §e" + new DecimalFormat("###,###,###,###,###,###").format(plugin.getData(((Player) sender).getUniqueId().toString()).getCpr()) + " §7Coin(s) pro Runde (CPR)§8."));
            sender.sendMessage("");
            return true;
        });

    }


}
